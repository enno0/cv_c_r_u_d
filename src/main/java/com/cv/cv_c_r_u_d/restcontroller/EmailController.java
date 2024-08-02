package com.cv.cv_c_r_u_d.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cv.cv_c_r_u_d.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String body) {
        emailService.sendSimpleEmail(toEmail, subject, body);
        return "Email sent successfully";
    }

    @GetMapping("/sendHtmlEmail")
    public String sendHtmlEmail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String body) {
        try {
            emailService.sendHtmlEmail(toEmail, subject, body);
            return "HTML Email sent successfully";
        } catch (MessagingException e) {
            return "Error sending HTML email: " + e.getMessage();
        }
    }
}
