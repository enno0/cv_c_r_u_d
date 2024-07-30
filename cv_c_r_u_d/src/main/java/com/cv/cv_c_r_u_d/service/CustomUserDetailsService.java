package com.cv.cv_c_r_u_d.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Configuration
@SuppressWarnings("deprecation")

public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String fetchedUsername;
        String fetchedPassword;
        List<String> fetchedRoles;

        try {
            fetchedUsername = jdbcTemplate.queryForObject(
                    "SELECT name FROM users WHERE name = ?",
                    new Object[] { username },
                    String.class);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("No user found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        try {
            fetchedPassword = jdbcTemplate.queryForObject(
                    "SELECT password FROM users WHERE name = ?",
                    new Object[] { username },
                    String.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No password found for user: {}", username, e);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        try {
            fetchedRoles = jdbcTemplate.queryForList(
                    "SELECT role FROM user_role WHERE name = ?",
                    new Object[] { username },
                    String.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No roles found for user: {}", username, e);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.withUsername(fetchedUsername)
                .password(passwordEncoder().encode(fetchedPassword))
                .roles(fetchedRoles.toArray(new String[0]))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
