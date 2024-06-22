package com.example.springsecuritystudy.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  IndexController {

  @GetMapping("/")
  public String index() {
    return "index";
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

  @GetMapping("/invalidSessionUrl")
    public String invalidSessionUrl() {
        return "invalidSessionUrl";
    }

  @GetMapping("/expiredUrl")
  public String expiredUrl() {
      return "expiredUrl";
  }
}
