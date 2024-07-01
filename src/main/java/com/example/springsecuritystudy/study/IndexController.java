package com.example.springsecuritystudy.study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class  IndexController {

  private final SessionInfoService sessionInfoService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/sessionInfo")
    public String sessionInfo() {
        sessionInfoService.sessionInfo();
        return "sessionInfo";
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
