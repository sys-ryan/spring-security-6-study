package com.example.springsecuritystudy.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/invalidSessionUrl", "/expiredUrl").permitAll()
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // default IF_REQUIRED
//                            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // default IF_REQUIRED
//                    .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId) // default changeSessionId
//                    .invalidSessionUrl("/invalidSessionUrl")
//                    .maximumSessions(1)
//                    .maxSessionsPreventsLogin(false)
//                    .expiredUrl("/expiredUrl")

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
