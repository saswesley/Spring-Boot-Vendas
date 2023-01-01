package io.github.saswesley.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.saswesley.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Bean
	public PasswordEncoder passwordEnconder() {	
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioService)
			.passwordEncoder(passwordEnconder());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/clientes/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/pedidos/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/produto/**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/usuario/**")
					.permitAll()
				.anyRequest().authenticated()
			.and()
				.httpBasic();
		;	
	}
	
}