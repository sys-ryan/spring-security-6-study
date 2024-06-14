package com.example.springsecuritystudy.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  IndexController {

  @GetMapping("/")
  public String index(String customParam) {
    if(customParam != null) {
      return "custom";
    } else {

    return "index";
    }
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
