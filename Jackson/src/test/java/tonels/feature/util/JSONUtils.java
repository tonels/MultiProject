package tonels.feature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            // 使jackson识别被@JsonFormat标识的字段
            .registerModule(new JavaTimeModule())
            // 设置jackson反序列化时遇到类中不存在的字段时不抛出异常
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private JSONUtils() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
//            log.error("java对象序列化失败:{}，java对象：{}", e.getMessage(), obj);
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonChar, Class<T> t) {
        try {
            return objectMapper.readValue(jsonChar, t);
        } catch (Exception e) {
//            log.error("json字符串反序列化失败，json字符串：{}，类型：{}", jsonChar, t.getName());
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonChar, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonChar, type);
        } catch (Exception e) {
//            log.error("json字符串反序列化失败，json字符串：{}，类型：{}", jsonChar, type.getType().getTypeName());
        }
        return null;
    }

    public static <T> T obj2T(Object obj, TypeReference<T> type) {
        try {
            return objectMapper.convertValue(obj, type);
        } catch (Exception e) {
//            log.error("json转换失败，Object：{}，类型：{}", obj, type.getType().getTypeName());
        }
        return null;

    }
}