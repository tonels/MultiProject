package querydsl.tonels.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import querydsl.tonels.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Utility class for Spring Security.
 */
@Component
public class SecurityUtils {

    @Autowired
    private UserService userService;

    private static UserService securityService;

    @PostConstruct
    public void init() {
        this.securityService = this.userService;
    }

    /**
     * 获取当前登录账户，即用户工号userCode
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                }).orElse(null);
    }

}
