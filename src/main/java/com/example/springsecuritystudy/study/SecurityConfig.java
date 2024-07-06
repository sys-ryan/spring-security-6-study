package com.example.springsecuritystudy.study;

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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SpaCsrfTokenRequestHandler csrfTokenRequestHandler = new SpaCsrfTokenRequestHandler();


        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/csrf", "/cookie", "/cookieCsrf").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfTokenRequestHandler)
                )
                .addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }


    /*
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //        CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
    //    csrfTokenRepository.setCookieHttpOnly(false);
    //    csrfTokenRepository.setCookieName("CSRF_RYAN");


            XorCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new XorCsrfTokenRequestAttributeHandler();
    //        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(null);  //  지연된 방식이 아닌 바로 토큰을 얻도록 하는 옵션

            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/csrf", "csrfToken", "/form", "/formCsrf").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    .csrf(Customizer.withDefaults());
    //                .csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository)
    //                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
    //                );

            return http.build();
        }
    */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1111")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}
