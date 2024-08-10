package com.cv.cv_c_r_u_d.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cv.cv_c_r_u_d.datatransferopject.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Login successful";
        } catch (AuthenticationException e) {
            return "Login failed: " + e.getMessage();
        }
    }

    @PostMapping("/logout")

    public String logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        SecurityContextHolder.clearContext();

        session = request.getSession(false);

        if (session != null) {

            session.invalidate();

        }

        return "Logout successful";

    }
}
