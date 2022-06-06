/**
 *
 */
package app.coreproject.controller;

import app.coreproject.contract.ResponseContract;
import app.coreproject.model.User;
import app.coreproject.payload.request.login.LoginRequestDto;
import app.coreproject.payload.request.login.RegisterRequestDto;
import app.coreproject.service.IUserService;
import app.coreproject.util.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author toduc
 *
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseContract<?> login(@Valid @RequestBody LoginRequestDto body, HttpServletResponse response) {

        String jwt = userService.login(body.getUsername(), body.getPassword());

        if (jwt == null) {
            return new ResponseContract<>(500, "login FAIL", "User name or password incorrect!");
        }
        if (jwt.length() < 20) {
            return new ResponseContract<>(Response.PERMISSION_DENY, jwt);
        }
        return new ResponseContract<>(200, "login SUCCESS", jwt);
    }

    @PostMapping("/register")
    public ResponseContract<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        User user = new User();
        BeanUtils.copyProperties(registerRequestDto, user);
        return userService.create(user);
    }


    @GetMapping("/check-health")
    public ResponseContract<?> checkHealth() throws InterruptedException {
        return new ResponseContract<>(200, "OK", new Date());
    }

}
