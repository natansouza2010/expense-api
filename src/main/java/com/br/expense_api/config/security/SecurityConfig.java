package com.br.expense_api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                         // Rotas públicas
//                        .anyRequest().authenticated() // Todas as outras rotas exigem login
//                )
//                .formLogin(withDefaults())
//                .oauth2Login(withDefaults());
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated() // Todas as requisições precisam de autenticação
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true) // Redireciona para /home após o login
                )
                .oauth2Client(withDefaults());
        return http.build();



    }

}

