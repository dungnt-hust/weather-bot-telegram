package app.coreproject.payload.request.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ductbm
 */
@Data
public class RegisterRequestDto {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    @JsonProperty("hCaptcha")
    private String hCaptcha;

}
