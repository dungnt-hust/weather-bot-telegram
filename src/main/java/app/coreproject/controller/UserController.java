/**
 *
 */
package app.coreproject.controller;

import app.coreproject.contract.ResponseContract;
import app.coreproject.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author To Duc
 *
 *         Dec 14, 2020--9:10:13 PM
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final IUserService userService;

    @GetMapping("/me")
    public ResponseContract<?> getUserInfo() {
        return userService.getUserInfo();
    }

    @DeleteMapping("/sysadmin/delete-user/{userId}")
    @PreAuthorize("hasRole('ROLE_SYS_ADMIN')")
    public ResponseContract<?> delete(@PathVariable("userId") Integer userId) {

        return userService.delete(userId);
    }
}
