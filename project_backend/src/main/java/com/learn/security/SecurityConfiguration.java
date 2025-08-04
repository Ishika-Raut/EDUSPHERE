package com.learn.security;

import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration //to declare config class use for configuration of class used for security
@EnableWebSecurity //customize spring security for web security support
@EnableMethodSecurity //enable method level annotations (guards url like httprequests)
//(@PreAuthorize , @PostAuthorize..) to specify  authorization rules
//(guards methods in servce,controller,etc)
@AllArgsConstructor

public class SecurityConfiguration {
	
	private final PasswordEncoder passwordEncoder;
	private final CustomJWTFilter customJWTFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable());

	    http.authorizeHttpRequests(auth -> auth
	        // Public endpoints
	        .requestMatchers("/swagger-ui/**", "/v*/api-docs/**", "/api/users/register", "/api/users/login/firststep","/api/users/login/secondstep").permitAll()

	        // ADMIN: Read-only access
	        .requestMatchers(HttpMethod.GET, "/api/users/getallusers**").hasRole("ADMIN")
	        .requestMatchers(HttpMethod.GET, "/admin/instructors/**").hasRole("ADMIN")
	        .requestMatchers(HttpMethod.GET, "/admin/courses/**").hasRole("ADMIN")

	        // INSTRUCTOR access
	        .requestMatchers(HttpMethod.POST, "/instructor/courses/**").hasRole("INSTRUCTOR")
	        .requestMatchers(HttpMethod.PUT, "/instructor/courses/**").hasRole("INSTRUCTOR")
	        .requestMatchers(HttpMethod.DELETE, "/instructor/courses/**").hasRole("INSTRUCTOR")
	        .requestMatchers(HttpMethod.POST, "/instructor/videos/**").hasRole("INSTRUCTOR")
	        .requestMatchers(HttpMethod.GET, "/instructor/videos/**").hasRole("INSTRUCTOR")

	        // LEARNER access
	        .requestMatchers(HttpMethod.POST, "/learner/enrollments/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.POST, "/learner/payments/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.POST, "/learner/cart/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.DELETE, "/learner/cart/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.POST, "/learner/feedback/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.GET, "/learner/certificates/**").hasRole("LEARNER")
	        .requestMatchers(HttpMethod.GET, "/learner/job-suggestions/**").hasRole("LEARNER")

	        // Any other request must be authenticated
	        .anyRequest().authenticated()
	    );

	    http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    http.addFilterBefore(customJWTFilter, UsernamePasswordAuthenticationFilter.class);
	    http.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint));

	    return http.build();
	}

	//configure spring to return spring security authentication manager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration mgr)throws Exception{
		return mgr.getAuthenticationManager();
	}
	
	
	
}	
