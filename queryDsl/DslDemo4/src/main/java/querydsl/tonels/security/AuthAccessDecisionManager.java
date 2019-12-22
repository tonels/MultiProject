package querydsl.tonels.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import querydsl.tonels.config.Constants;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("accessDecisionManager")
public class AuthAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        //验证是否鉴权
        if (!configAttributes.isEmpty()) {
            String authorize = null;
            for (ConfigAttribute configAttribute : configAttributes) {
                System.out.println("-------AccessDecisionManager--------------" + configAttribute.toString());
                authorize = configAttribute.toString();
                break;
            }
            if (Constants.NO_AUTHORIZE.equals(authorize)) {
                return;
            }
        }
        //在验证是否登录
        System.out.println("-------AccessDecisionManager--------------" + SecurityUtils.getCurrentUserLogin());
        if (Constants.ANONYMOUS_USER.equals(SecurityUtils.getCurrentUserLogin())) {
            throw new AccessDeniedException(" 没有权限访问！ ");
        }
        //在验证是否有权限
        if (object instanceof FilterInvocation) {
            FilterInvocation web = (FilterInvocation) object;
            String uri = web.getRequestUrl();
            System.out.println("-------AccessDecisionManager--------------" + uri);

            String urlCode = this.getUrlCode(uri);
            // todo  判断URL是否拥有资源,循环下面集合查询URL是否存在其中
            // 判断该URL标识，是否在该用户所有的权限列中
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            List<? extends GrantedAuthority> collect = authorities.stream().collect(Collectors.toList());
            for (GrantedAuthority grantedAuthority : collect) {
                String authority = grantedAuthority.getAuthority();
                if (authority.equals(urlCode)) {
                    return;
                }
            }
        }
        // 没有权限configAttributes
        throw new AccessDeniedException(" 没有权限访问！ ");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 从请求中获取URL标识
     *
     * @param uri
     */
    String getUrlCode(String uri) {
        if (uri.contains("\\?")) {
            uri = uri.split("\\?")[0];
            return uri;
        }
        return uri;
    }
}
