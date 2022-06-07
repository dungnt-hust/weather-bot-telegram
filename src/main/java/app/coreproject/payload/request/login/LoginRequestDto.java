package app.coreproject.payload.request.login;

import app.coreproject.payload.Request;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ductbm
 */
@Data
public class LoginRequestDto implements Request {

    @NotNull(message = "4000001")
    @NotEmpty(message = "4000001")
    private String username;
    private String password;

    private String group;

    private String session;
}
