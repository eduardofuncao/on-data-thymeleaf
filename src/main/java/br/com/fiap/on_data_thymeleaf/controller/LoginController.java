package br.com.fiap.on_data_thymeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Controller
    public class LogoutController {

        @PostMapping("/logout")
        public String logout(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "redirect:/login?logout";
        }
    }

}
