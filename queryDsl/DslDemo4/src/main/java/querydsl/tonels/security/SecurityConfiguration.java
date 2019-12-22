package querydsl.tonels.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import querydsl.tonels.security.handler.ExceptionHandler;
import querydsl.tonels.security.handler.LoginFailureHandler;
import querydsl.tonels.security.handler.LoginSuccessHandler;
import querydsl.tonels.security.handler.LogoutSuccessHandlerImpl;
import querydsl.tonels.security.jwt.JwtTokenFilter;
import querydsl.tonels.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .formLogin()
            .successHandler(loginSuccessHandler())
            .failureHandler(authenticationFailureHandler())
        .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
        .and()
            .logout()
            .logoutSuccessHandler(logoutSuccessHandler())
        .and()
            .userDetailsService(userDetailsService)
            //OPTIONS请求全部放行
            .authorizeRequests()
            .antMatchers(/*HttpMethod.OPTIONS,*/ "/**").permitAll();
//            .antMatchers("/auth/login").permitAll()
//            .antMatchers("/auth/logout").permitAll();

      //使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() {
        return new JwtTokenFilter();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new ExceptionHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new LoginFailureHandler();
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandlerImpl();
    }
}
