package com.example.springsecuritystudy.study;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("customWebSecurity")
public class CustomWebSecurity {

    public boolean check(Authentication authentication, HttpServletRequest request) {
        System.out.println("customWebSecurity.check");
        System.out.println("authentication = " + authentication.getPrincipal());
        return authentication.isAuthenticated() && authentication.getPrincipal() != "anonymousUser";
    }
}
