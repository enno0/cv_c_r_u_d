package com.cv.cv_c_r_u_d.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig {

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Apply this security config only to paths starting with /api/
                .csrf().disable() // Disable CSRF for REST API
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/**").permitAll() // Allow access to /api/auth endpoint
                        .anyRequest().authenticated())
                .httpBasic(); // Use HTTP Basic authentication for REST API

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
