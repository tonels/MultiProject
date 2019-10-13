package demo1.log;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReflectUtils {

    public static <T> void dynamicSet(T t, String propertyName, Object param) {
        try {
            Field field = t.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(t, param);
        } catch (Exception e) {
        }
    }

    public static <T> Object dynamicGet(T t, String propertyName) {
        try {
            Field field = t.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(t);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取指定报名下指定注解的注解信息
     *
     * @param packageStr
     * @param annotationClass
     * @return
     */


    public static List<Annotation> getJobAnnotation(String packageStr, Class<? extends Annotation> annotationClass) {
        List<Annotation> annotationList = new ArrayList<Annotation>();


        Reflections reflections = new Reflections(packageStr);

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotationClass);

        for (Class<?> cl : classes) {
            Annotation jobAnnotation = cl.getAnnotation(annotationClass);
            annotationList.add(jobAnnotation);
        }
        return annotationList;
    }


}
