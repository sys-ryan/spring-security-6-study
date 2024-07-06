package com.example.springsecuritystudy.study;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

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

    @PostMapping("/csrf")
    public String csrf() {
        return "csrf applied";
    }

    @GetMapping("/csrfToken")
    public String csrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//      CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        logger.info("csrfToken: {}", csrfToken);
        logger.info("csrfToken.getToken(): {}", csrfToken.getToken());
        logger.info("csrfToken.getHeaderName(): {}", csrfToken.getHeaderName());
        logger.info("csrfToken.getParameterName(): {}", csrfToken.getParameterName());

        String token = csrfToken.getToken();
        return token;
    }

    @PostMapping("formCsrf")
    public CsrfToken formCsrf(CsrfToken csrfToken) {
        return csrfToken;
    }

    @PostMapping("/cookieCsrf")
    public CsrfToken cookieCsrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
