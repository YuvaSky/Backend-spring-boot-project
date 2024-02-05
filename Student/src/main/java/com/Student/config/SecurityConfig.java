package com.Student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.Student.security.CustomUserDetailService;
import com.Student.security.JwtAuthenticationEntryPoint;
import com.Student.security.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SecurityConfig {
	
	public static final String[] PUBLIC_URLS= {"/api/v1/auth/**",
			"/v3/api-docs/",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",
			"/api/auth/**"
			
	};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationfilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(PUBLIC_URLS).permitAll()
        .requestMatchers("/api/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST).permitAll()
        .requestMatchers(HttpMethod.GET).permitAll()
            .anyRequest().authenticated()
        )
        .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
        .authenticationProvider(daoAuthenticationProvider());
    	
 		DefaultSecurityFilterChain build = http.build();
 		return build;
}
    
    @Bean
     DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
    	provider.setUserDetailsService(this.customUserDetailService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    @Bean
     AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
    	return configuration.getAuthenticationManager();
    }
}


