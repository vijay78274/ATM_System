package com.example.ATM.system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    MyUserDetailsServices userDetailsServices;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        // .csrf(csrf -> csrf.disable()) 
        .authorizeHttpRequests(request -> 
            request.anyRequest().authenticated()
                // .requestMatchers("/login", "/css/**", "/js/**").permitAll() 
                // .requestMatchers("/atm/**").authenticated() 
        ).formLogin(login -> login
        .defaultSuccessUrl("/atm/main", true)  //  Redirect after successful login
        .permitAll()
    )
    .logout(logout -> logout
        .logoutUrl("/logout")  //  Default logout endpoint
        .logoutSuccessUrl("/login?logout")  //  Redirect after logout
        .permitAll()
    )
    .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsServices);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

