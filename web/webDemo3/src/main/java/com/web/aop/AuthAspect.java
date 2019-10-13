//package com.web.aop;
//
//import com.web.annotation.AuthorizationImpl;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Configuration
//public class AuthAspect {
//    @Autowired
//    private static  AuthorizationImpl authorizationImpl;
//
//    @Before("@annotation(com.<packagepath>.Authorized) && args(request,..)")
//    public static void before(HttpServletRequest request){
//        if (!(request instanceof HttpServletRequest)) {
//            throw
//                    new RuntimeException("request should be HttpServletRequesttype");
//        }
//
//        if(authorizationImpl.authorize(request.getHeader("Authorization"))){
//            req.setAttribute(
//                    "userSession",
//                    "session information which cann be acces in controller"
//            );
//        }else {
//            throw new RuntimeException("auth error..!!!");
//        }
//
//    }
//
//}
