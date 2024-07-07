package com.example.springsecuritystudy.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ApplicationContext context) throws Exception {
        DefaultHttpSecurityExpressionHandler expressionHandler = new DefaultHttpSecurityExpressionHandler();
        expressionHandler.setApplicationContext(context);

        WebExpressionAuthorizationManager authorizationManager = new WebExpressionAuthorizationManager("@customWebSecurity.check(authentication, request)");
        authorizationManager.setExpressionHandler(expressionHandler);

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new CustomRequestMatcher("/admin")).hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/custom/**").access(authorizationManager)
//                        .requestMatchers("/user/{name}")
//                        .access(new WebExpressionAuthorizationManager("#name == authentication.name"))
//
//                        .requestMatchers("/admin/db")
//                        .access(new WebExpressionAuthorizationManager("hasAuthority('ROLE_DB') or hasAuthority('ROLE_ADMIN')"))

                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1111")
                .roles("USER").build();

        UserDetails manager = User.withUsername("manager")
                .password("{noop}1111")
                .roles("MANAGER").build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}1111")
                .roles("ADMIN", "WRITE").build();

        return new InMemoryUserDetailsManager(user, manager, admin);
    }
}
