package com.example.springsecuritystudy.study;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  IndexController {

  @GetMapping("/")
  public Authentication index(Authentication authentication) {
    return authentication;
  }

  @GetMapping("/loginPage")
  public String loginPage() {
    return "loginPage";
  }

  @GetMapping("/home")
  public String home() {
    return "home";
  }

  @GetMapping("/faileddd")
  public String failed() {
    return "failed";
  }
}
