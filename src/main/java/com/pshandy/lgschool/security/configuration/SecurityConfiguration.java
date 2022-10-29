package com.pshandy.lgschool.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/homepage");


        http.authorizeRequests()
                .antMatchers("/homepage").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/teacher/**").hasRole("TEACHER")
                .antMatchers(
                        "/css/**/**",
                        "/js/**",
                        "/assets/**/**",
                        "/error/**/**")
                .permitAll();
//
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//        http.authorizeRequests()
//                .anyRequest().permitAll();

        return http.build();

    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
