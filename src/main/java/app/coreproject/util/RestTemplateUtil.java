package app.coreproject.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author ductbm
 */
@Component
public class RestTemplateUtil {


    public static <T>  ResponseEntity<T> callApi(HttpEntity<?> requestEntity, String url, HttpMethod method, String custom, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<T> typeReference = ParameterizedTypeReference.forType(clazz);
        if (!StringUtils.isEmpty(custom)) {
            return restTemplate.exchange(url, method, requestEntity, typeReference, custom);
        }else {
            return restTemplate.exchange(url, method, requestEntity, typeReference);
        }
    }

}
