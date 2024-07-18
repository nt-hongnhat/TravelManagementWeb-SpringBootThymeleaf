package com.nthn.springbootthymeleaf.config;

import com.nthn.springbootthymeleaf.config.handlers.LoginSuccessHandler;
import com.nthn.springbootthymeleaf.config.handlers.LogoutSuccessHandler;
import com.nthn.springbootthymeleaf.constants.EndpointConstants;
import com.nthn.springbootthymeleaf.constants.FormatConstants;
import com.nthn.springbootthymeleaf.constants.Role;
import com.nthn.springbootthymeleaf.service.AccountService;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final LogoutSuccessHandler logoutSuccessHandler;
	
	private final AccountService accountService;
	
	private final DataSource dataSource;
	
	private final LoginSuccessHandler loginSuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(accountService);
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}
	
	
	@Autowired
	public void configureGlobal(@NotNull AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Override
	protected void configure(@NotNull HttpSecurity http) throws Exception {
		http.addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(EndpointConstants.DEFAULT, EndpointConstants.LOGIN, EndpointConstants.LOGOUT,
						EndpointConstants.REGISTER,
						"/tours/**", "/news/**")
				.permitAll().antMatchers("/profile", "/booking/**", "/comment")
				.hasRole(GlobalConstants.Role.CUSTOMER)
				.antMatchers(String.format(FormatConstants.ENDPOINT_MATCHES,
						EndpointConstants.DASHBOARD))
				.hasRole(Role.ADMIN).anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedHandler().and().formLogin().loginProcessingUrl(EndpointConstants.LOGIN)
				.loginPage(EndpointConstants.LOGIN)
				.defaultSuccessUrl(EndpointConstants.DEFAULT).failureUrl("/login?error")
				.successHandler(loginSuccessHandler).usernameParameter(GlobalConstants.Key.USERNAME)
				.passwordParameter(GlobalConstants.Key.PASSWORD).and().logout()
				.logoutSuccessHandler(logoutSuccessHandler).permitAll().and().rememberMe()
				.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(24 * 60 * 60);
	}
	
	// Token stored in Table (Persistent_Logins)
	//    @Bean
	//    public PersistentTokenRepository persistentTokenRepository() {
	//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	//        db.setDataSource(dataSource);
	//        return db;
	//    }
	
	// Token stored in Memory (Of Web Server).
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		return new InMemoryTokenRepositoryImpl();
	}
}