package querydsl.tonels.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("-LogoutSuccessHandler--------退出成功");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
//        response.getWriter().write(ResultBean.ok().toString());
        response.getWriter().write("error ...");
    }

}
