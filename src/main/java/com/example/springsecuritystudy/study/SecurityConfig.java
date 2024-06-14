package com.example.springsecuritystudy.study;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/logoutSuccess").permitAll()
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                    .logoutSuccessUrl("/logoutSuccess")
                    .logoutSuccessHandler((req, res, auth) -> {
                      res.sendRedirect("/logoutSuccess");
                    })
                    .deleteCookies("JESSIONID", "remember-me")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .addLogoutHandler((req, res, auth) -> {
                      HttpSession session = req.getSession();
                      session.invalidate();
                      SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
                      SecurityContextHolder.getContextHolderStrategy().clearContext();
                    })
                    .permitAll()
            );


    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user")
        .password("{noop}1111")
        .roles("USER").build();

    return new InMemoryUserDetailsManager(user);
  }
}
