package cn.yscme.blog.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class JsonUtil {
 
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager
            .getLogger(JsonUtil.class);
 
    /**
     * 将对象转成json
     *
     * @param object 被转的对象
     * @return 返回json
     */
    public static String objToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("对象转json失败", e);
        }
        return null;
    }
 
    /**
     * 将json转成对象
     *
     * @param jsonString  被转的json
     * @param objectClass 转换后对象的class
     * @param <T>         泛型
     * @return 返回object
     */
    public static <T> T jsonToObject(String jsonString, Class<T> objectClass) {
        try {
            return objectMapper.readValue(jsonString, objectClass);
        } catch (Exception e) {
            logger.error("json转对象失败", e);
        }
        return null;
    }
 
    /**
     * 将json转成map
     *
     * @param jsonString 被转的json
     * @return 返回map
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            logger.error("json转map失败", e);
        }
        return null;
    }
}
