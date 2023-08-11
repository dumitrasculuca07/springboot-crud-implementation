package com.project.AdminPanel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.sql.SQLData;

//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.sql;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/cssBootstrap/**").permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/departmentOverview").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/register").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/department/*/edit").hasRole("ADMIN")
                        .requestMatchers("/department/*/delete").hasRole("ADMIN")
                        .requestMatchers("/department/add").hasRole("ADMIN")
                        .requestMatchers("/department/*").hasAnyRole( "USER","ADMIN")
                        .requestMatchers("/department/*/add").hasRole( "ADMIN")
                        .requestMatchers("/department/*/editEmployee").hasRole( "ADMIN")
                        .requestMatchers("/department/*/deleteEmployee").hasRole( "ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/departmentOverview")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/access-denied"));
        return http.build();
    }
}