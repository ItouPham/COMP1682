package com.final_project.chriscosmetic.security;

import com.final_project.chriscosmetic.constant.Role;
import com.final_project.chriscosmetic.repository.AccountRepository;
import com.final_project.chriscosmetic.repository.CartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private AccountRepository accountRepository;
	private CartRepository cartRepository;

	public WebSecurityConfig(AccountRepository accountRepository, CartRepository cartRepository) {
		this.accountRepository = accountRepository;
		this.cartRepository = cartRepository;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService(accountRepository, cartRepository);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.and().authorizeRequests()
				.antMatchers("/cart").authenticated()
				.antMatchers("/product/product-detail/addToCart").authenticated()
				.antMatchers("/checkout").authenticated()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.permitAll()
				.and()
				.logout().logoutSuccessUrl("/").permitAll()
				.and().exceptionHandling().accessDeniedPage("/403");
	}
}
