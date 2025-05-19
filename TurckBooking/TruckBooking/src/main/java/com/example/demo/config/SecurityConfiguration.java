package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
    @Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		return http
				.csrf(Customizer -> Customizer.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/driver/**").hasRole("DRIVER").requestMatchers("/users/**")
						.hasAnyRole("DRIVER","USER").requestMatchers("/public/**").permitAll()
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	
    	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    	provider.setUserDetailsService(userDetailsService);
    	
    	return provider;
    }
	
	

}
