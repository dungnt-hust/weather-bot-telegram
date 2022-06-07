package app.coreproject.service.impl;

import app.coreproject.constant.DefaultResponse;
import app.coreproject.constant.UserConstant;
import app.coreproject.contract.ResponseContract;
import app.coreproject.model.User;
import app.coreproject.repository.UserRepository;
import app.coreproject.security.TokenProvider;
import app.coreproject.security.UserPrincipal;
import app.coreproject.service.IUserService;
import app.coreproject.util.JsonUtil;
import app.coreproject.util.Response;
import app.coreproject.util.UserPrincipalInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final JsonUtil jsonUtil;

    private final String[] availUpdatePermission = {"ROLE_USER", "ROLE_ADMIN"};

    @Override
    public String login(String userName, String pass) {
        try {
            Optional<User> userOptional = userRepository.findByUsername(userName);
            User user = null;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (!user.getStatus().equals(UserConstant.UserStatus.ACTIVE.getValue())) {
                    return "USER_INACTIVE";
                }
            } else {
                return "USER_NOT_EXIST";
            }
            if (checkPass(pass, user.getPassword())) {
                UserPrincipal userPrincipal = UserPrincipal.create(user);
                return tokenProvider.createToken(userPrincipal);
            } else {
                return "INCORRECT_PASSWORD";
            }

        } catch (Exception e) {
            logger.error("lỗi login", e);
            return "ERROR_TOKEN";
        }
    }

    @Override
    public ResponseContract<?> create(User user) {

        try {
            if (isUserNameExist(user.getUsername())) {
                return new ResponseContract<>(Response.BAD_REQUEST, "Username đã tồn tại!");
            }
            user.setPermission("ROLE_USER");
            user.setStatus(UserConstant.UserStatus.ACTIVE.getValue());
            user.setPassword(encrytePassword(user.getPassword()));
            user.setVersion(1);
            return new ResponseContract<>(Response.SUCCESS, userRepository.create(user).getUsername());
        } catch (Exception e) {
            logger.error("lỗi create", e);
            return new ResponseContract<>(Response.SERVER_ERROR, "Tạo mới user lỗi!");
        }
    }

    @Override
    public ResponseContract<?> update(User user) {
        try {

            if (isUserNameExist(user.getUsername())) {
                return new ResponseContract<>(Response.BAD_REQUEST, "Username đã tồn tại!");
            }
            int result = userRepository.update(user);
            if (result > 0) {
                return new ResponseContract<>(Response.SUCCESS, null);
            } else {
                return new ResponseContract<>(Response.DB_ERROR, null);
            }
        } catch (Exception e) {
            logger.error("UserService - update ", e);
            return new ResponseContract<>(Response.SERVER_ERROR, null);
        }

    }

    @Override
    public ResponseContract<?> delete(int userId) {
        try {
            log.info("delete request:{} by {}", userId, UserPrincipalInfo.getUserPrincipalInfo().getUserId());
            User user = userRepository.findByUserId(userId).orElseGet(null);
            user.setStatus(UserConstant.UserStatus.INACTIVE.getValue());
            userRepository.update(user);
            return DefaultResponse.defaultSuccessResponse();
        } catch (Exception e) {
            log.error("resetPassword error input {}", userId, e);
            return DefaultResponse.defaultExceptionResponse();
        }
    }

    @Override
    public ResponseContract<?> getUserInfo() {

        try {
            int userId = UserPrincipalInfo.getUserPrincipalInfo().getUserId();
            User user = userRepository.findByUserId(userId).orElse(new User());
            user.setPassword("*******");
            return new ResponseContract<>(Response.SUCCESS, user);
        } catch (Exception e) {
            logger.error("UserService - getUserInfo ", e);
            return new ResponseContract<>(Response.SERVER_ERROR, null);
        }


    }

    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean checkPass(String pass, String dataBasePass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pass, dataBasePass);
    }

    private boolean isEmailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        } else
            return true;
    }

    private boolean isUserNameExist(String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            return true;
        } else
            return false;
    }

    private boolean isPhoneExist(String phone) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent()) {
            return true;
        } else
            return false;
    }
}
