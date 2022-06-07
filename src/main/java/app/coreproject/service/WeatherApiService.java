package app.coreproject.service;

import app.coreproject.util.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WeatherApiService {
    private final HttpEntity request;

    @Value("${open-weather-api.url}")
    private String apiURL;

    @Value("${open-weather-api.api-key}")
    private String apiKey;

    public Map<String, String> getInfomation(String place) throws JSONException {
        String api = apiURL.replace(":key", "" + apiKey);
        api = api.replace(":place", "" + place);
//        String api = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=bcdc49b2f9c44f3fae4436aa6981c929&units=metric&lang=vi";
        try {
            ResponseEntity<String> response = RestTemplateUtil.callApi(request, api, HttpMethod.GET, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                String jsonStr = response.getBody();
                JSONObject obj = new JSONObject(jsonStr);
                JSONArray arr = obj.getJSONArray("weather");
                String description = arr.getJSONObject(0).getString("description");
                String icon = arr.getJSONObject(0).getString("icon");
                String temp = obj.getJSONObject("main").getString("temp");
                String icon_url = "";
                switch (icon) {
                    case "01d":
                    case "01n":
                        icon_url = "https://i.imgur.com/9CGjkOE.jpg";
                        break;
                    case "02d":
                    case "02n":
                        icon_url = "https://i.imgur.com/CRh04K2.jpg";
                        break;
                    case "03d":
                    case "03n":
                        icon_url = "https://i.imgur.com/zHuhzVo.jpg";
                        break;
                    case "04d":
                    case "04n":
                        icon_url = "https://i.imgur.com/riGvdrP.jpg";
                        break;
                    case "09d":
                    case "09n":
                        icon_url = "https://i.imgur.com/XHUnTV6.jpg";
                        break;
                    case "10d":
                    case "10n":
                        icon_url = "https://i.imgur.com/7ECPQGA.jpg";
                        break;
                    case "11d":
                        icon_url = "https://i.imgur.com/dMj6Rt9.jpg";
                        break;
                    case "50d":
                    case "50n":
                        icon_url = "https://i.imgur.com/j4jSE6N.jpg";
                        break;
                    default:
                        icon_url = "http://openweathermap.org/img/wn/"+ icon +"@2x.png";
                        break;
                }

                Map<String, String> map = new HashMap<String, String>();
                map.put("description", description);
                map.put("photo", icon_url);
                map.put("temp", temp);

                return map;
            } else {
                System.out.println("Request Failed");
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
}
