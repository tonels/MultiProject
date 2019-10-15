package demo1.log;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义注解处理类 + AOP
 * 存操作记录
 * 打印日志
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Autowired
    private OperateLogRepository logRepository;

    /**
     * 定义一个切入点. ("execution(public * com.kfit.*.web..*.*(..))") 解释下： 第一个 *
     * 代表任意修饰符及任意返回值. 第二个 * 任意包名 第三个 * 代表任意方法. 第四个 * 定义在web包或者子包 第五个 * 任意方法 ..
     * 匹配任意数量的参数.
     */
    @Pointcut("execution(public * demo1.controller..*.*(..)) "
            + " && @annotation(demo1.log.SystemLog)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        // log.info("环绕通知开始........");
//        String username = SecurityUtils.getCurrentUserName();

        String username = "ss";
        // 入参 value
        Object[] args = joinPoint.getArgs();
        // 入参名称
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> params = new HashMap<>();
        // 获取所有参数对象
        for (int i = 0; i < args.length; i++) {
            if (null != args[i]) {
                if (args[i] instanceof BindingResult) {
                    params.put(paramNames[i], "bindingResult");
                } else {
                    params.put(paramNames[i], args[i]);
                }
            } else {
                params.put(paramNames[i], "无");
            }
        }
        Map<String, Object> values = getControllerAnnotationValue(joinPoint);
        String opType = values.get("opType").toString();
        String module = values.get("module").toString();
        String business = values.get("business").toString();
        String primaryKeyName = values.get("primaryKeyName").toString();
        int primaryKeySort = Integer.parseInt(values.get("primaryKeySort").toString());
        Class<?> primaryKeyBelongClass = (Class<?>) values.get("primaryKeyBelongClass");

        Object primaryKeyValue = null;
        if (StringUtils.isNotBlank(primaryKeyName) && OperateType.valueOf(opType) == OperateType.delete) {
            primaryKeyValue = args[primaryKeySort];
        }
        // 切面返回值
        Object returnValue = joinPoint.proceed();
        if (OperateType.valueOf(opType) != OperateType.delete) {
            // 主要目的是为了获取方法保存成功的返回数据格式，以获取保存成功的数据id
            // 此处要限制新增返回成功的格式，return ok("具体操作信息", new MapBean("此处为实体主键属性名称",
            // primaryKeyValue));
            // 不然自己也可定义格式，进行拆分获取主键
            primaryKeyName = getPrimaryKeyName(primaryKeyBelongClass).toString();
            primaryKeyValue = ReflectUtils.dynamicGet(returnValue, primaryKeyName);
            if (primaryKeyValue == null || primaryKeyValue.toString().equals("")) {// 处理service层返回ResultBean
                Object result = ReflectUtils.dynamicGet(returnValue, "result");
                if (result != null) {
                    primaryKeyValue = ReflectUtils.dynamicGet(result, primaryKeyName);
                } else {
                    primaryKeyValue = args[primaryKeySort];
                }
            }
        }

        OperateLog operateLog = new OperateLog();

        //
        if (JSONUtil.toJsonStr(params).length() <= 2000) {
            operateLog.setData(JSONUtil.toJsonStr(params));
        }

        operateLog.setUserId(1L);
        operateLog.setUserName(username == null ? "系统" : username);
        operateLog.setModule(module);
        operateLog.setOpType(opType);
        operateLog.setBusiness(business);

        String recordId = null;
        if (primaryKeyValue instanceof Object[]) {
            recordId = Arrays.toString((Object[]) primaryKeyValue);
        } else {
            recordId = primaryKeyValue.toString();
        }
        operateLog.setRecordId(recordId);
        operateLog.setCreateTime(new Date());
        logRepository.save(operateLog);
        log.info(">>>操作日志：{}", operateLog);
        return returnValue;
    }

    /**
     * 获取class的主键字段名 主键值
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object getPrimaryKeyName(Class<?> clazz) throws Exception {
        Object param = null;
        // 递归获取父子类所有的field
        Class tempClass = clazz;
        // 当父类为null的时候说明到达了最上层的父类(Object类
        while (tempClass != null && !StringUtils.equals(tempClass.getName().toLowerCase(), "java.lang.object")) {
            Field[] fields = tempClass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                // boolean类型不必判断，因实体里包含boolean类型的属性，getter方法是以is开头，不是get开头
                if (field.getType().equals(Boolean.class) || field.getType().getName().equals("boolean")) {
                    continue;
                }
                if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                    continue;
                }
                String getterMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = tempClass.getDeclaredMethod(getterMethod);

                // 字段上是否存在@Id注解
                Object primaryAnnotation = field.getAnnotation(Id.class);// for hibernate
                if (primaryAnnotation == null)
                    primaryAnnotation = field.getAnnotation(org.springframework.data.annotation.Id.class);// for spring
                // data
                // getter方法上是否存在@Id注解
                if (primaryAnnotation == null)
                    primaryAnnotation = method.getAnnotation(Id.class);
                if (primaryAnnotation == null)
                    primaryAnnotation = method.getAnnotation(org.springframework.data.annotation.Id.class);
                // 存在@Id注解，则说明该字段为主键
                if (primaryAnnotation != null) {
                    /* String primaryKeyName = field.getName(); */
                    param = field.getName();
                    break;
                }
            }
            if (param != null && StringUtils.isNotBlank(param.toString())) {
                break;
            }
            // 得到父类赋值给tempClass
            tempClass = tempClass.getSuperclass();
        }
        if (param == null) {
            throw new Exception(clazz.getName() + "实体，未设置主键");
        }
        return param;
    }

    /**
     * 获取@SystemLog 注解上信息
     *
     * @param joinPoint
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getControllerAnnotationValue(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Map<String, Object> map = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == arguments.length) {
                    // 取入参数据
                    String description = method.getAnnotation(SystemLog.class).description();
                    String module = method.getAnnotation(SystemLog.class).module().name();
                    String opType = method.getAnnotation(SystemLog.class).opType().name();
                    String primaryKeyName = method.getAnnotation(SystemLog.class).primaryKeyName();
                    int primaryKeySort = method.getAnnotation(SystemLog.class).primaryKeySort();
                    Class<?> clazz = method.getAnnotation(SystemLog.class).primaryKeyBelongClass();
                    map.put("module", module);
                    map.put("opType", opType);
                    map.put("business", description);
                    map.put("primaryKeyName", primaryKeyName);
                    map.put("primaryKeySort", primaryKeySort);
                    map.put("primaryKeyBelongClass", clazz);
                    break;
                }
            }
        }
        return map;
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        // log.info("WebLogAspect.doAfterReturning()");
    }
}
