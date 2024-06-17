package com.example.springsecuritystudy.study;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  IndexController {

  @GetMapping("/")
  public String index() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    System.out.println("authentication = " + authentication);
    return "";
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
