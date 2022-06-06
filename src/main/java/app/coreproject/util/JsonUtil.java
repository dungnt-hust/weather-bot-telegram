package app.coreproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * The Class JsonUtil.
 */
@Component
public class JsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonUtil() {
    }

    /**
     * Gets the generic object.
     *
     * @param <T>   the generic type
     * @param input the input
     * @param clazz the clazz
     * @return the generic object
     */
    public <T> T getEntityFromJsonObj(Object input, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.convertValue(input, clazz);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    /**
     * Gets the generic object list.
     *
     * @param input   the input
     * @param typeRef the type ref: new TypeReference<List<xxx>>() {}
     * @return the generic object list
     */
    public <T> T getGenericObjectList(Object input, TypeReference<T> typeRef) {
        return objectMapper.convertValue(input, typeRef);
    }

    public <T> T getGenericStringList(String input, TypeReference<T> typeRef) {
        try {
            return objectMapper.readValue(input, typeRef);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Cast entity class to JsonObject
     *
     * @return JSONObject. If null, return new instance
     */
    public JSONObject getJsonObjFromEntity(Object entity) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(objectMapper.writeValueAsString(entity));
        } catch (JsonProcessingException | ParseException e) {
            // log
        }
        return new JSONObject();
    }

    /**
     * Cast entity class to Json String
     *
     * @return JSON String. If null, return empty
     */
    public String getJsonStrFromEntity(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            // log
        }
        return "";
    }

    public <T> T getEntityFromJsonStr(String input, Class<T> clazz) {
        try {
            return objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public <T> T getEntityFromJsonStr(String input, TypeReference valueTypeRef) {
        try {
            return objectMapper.readValue(input, valueTypeRef);
        } catch (IOException e) {
            return null;
        }
    }

    public String getValueFromJsonStr(String jsonStr, String key, boolean trimValue) {
        try {
            Map<String, String> map =
                    objectMapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {
                    });
            String val = map.get(key);
            if (val != null) {
                if (trimValue) {
                    val = val.trim();
                }
                return val;
            }
        } catch (Exception e) {
            // log
        }
        return "";
    }

    public String getValueFromJsonObj(JSONObject jsonObj, String key, boolean trimValue) {
        try {
            String val = jsonObj.get(key).toString();
            if (val != null) {
                if (trimValue) {
                    val = val.trim();
                }
                return val;
            }
        } catch (Exception e) {
            // log
        }
        return "";
    }

    public String printObjectToLog(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // log
        }
        return "";
    }
}
