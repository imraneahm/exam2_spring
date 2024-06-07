package com.example.exam2_spring.config;

import com.example.exam2_spring.entities.Employe;
import com.example.exam2_spring.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InMemoryUserDetailsServiceConfig {

    @Autowired
    private EmployeRepository employeRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        for (Employe employe : employeRepository.findAll()) {
            manager.createUser(
                    org.springframework.security.core.userdetails.User
                            .withUsername(employe.getUsername())
                            .password(passwordEncoder().encode(employe.getPassword()))
                            .roles(employe.getPost().name())
                            .build()
            );
        }
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
