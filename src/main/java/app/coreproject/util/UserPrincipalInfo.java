package app.coreproject.util;

import org.springframework.security.core.context.SecurityContextHolder;

import app.coreproject.security.UserPrincipal;

public class UserPrincipalInfo {
    public static UserPrincipal getUserPrincipalInfo() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userPrincipal;
    }

}
