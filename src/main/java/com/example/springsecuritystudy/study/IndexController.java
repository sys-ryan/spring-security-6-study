package com.example.springsecuritystudy.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/photos")
    public String photos() {
        return "photos";
    }

    @GetMapping("/oauth/login")
    public String login() {
        return "oauthLogin";
    }
}
