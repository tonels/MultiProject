//package parking.configuration;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.ldap.authentication.ad.ActiveDirectoryAliasLdapAuthenticationProvider;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import parking.security.RESTAuthenticationEntryPoint;
//import parking.security.RESTAuthenticationFailureHandler;
//import parking.security.RESTAuthenticationSuccessHandler;
//
//import javax.annotation.Resource;
//
///**
// *
// * @author gustavojotz
// *
// */
//@Configuration
//@EnableTransactionManagement
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Resource
//	private RESTAuthenticationEntryPoint authenticationEntryPoint;
//
//	@Resource
//	private RESTAuthenticationFailureHandler authenticationFailureHandler;
//
//	@Resource
//	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
//
//	@Autowired
//	private LdapSettings settings;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic();
//		http.csrf().disable();// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());;
//		http.authorizeRequests().anyRequest().fullyAuthenticated();
//		http.formLogin().permitAll();
//		if (!settings.getLogin_form()) {
//			http.formLogin().successHandler(authenticationSuccessHandler);
//			http.formLogin().failureHandler(authenticationFailureHandler);
//			http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
//		}
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/health", "/uptime");
//	}
//
//	@Configuration
//	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//		@Resource
//		private LdapSettings settings;
//
//		@Override
//		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			ActiveDirectoryAliasLdapAuthenticationProvider provider =
//					new ActiveDirectoryAliasLdapAuthenticationProvider(settings.getDomain(), settings.getDomainAlias(), settings.getUrl());
//			provider.setUseAuthenticationRequestCredentials(true);
//			auth.authenticationProvider(provider);
//		}
//
//	}
//
//	@Configuration
//	@EnableConfigurationProperties
//	@ConfigurationProperties(prefix = "ldap")
//	@Getter
//	@Setter
//	protected static class LdapSettings {
//		private String url;
//		private String domain;
//		private String domainAlias;
//		private Boolean login_form;
//	}
//
//}
