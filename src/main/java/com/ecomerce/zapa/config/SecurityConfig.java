package com.ecomerce.zapa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecomerce.zapa.security.JwtFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ✅ DESACTIVAR CSRF
            .csrf(csrf -> csrf.disable())

            // ✅ CONFIGURAR PERMISOS
            .authorizeHttpRequests(auth -> auth

                // 🔓 LOGIN Y REGISTRO SIN TOKEN
                .requestMatchers("/api/usuarios/login").permitAll()
                .requestMatchers("/api/usuarios").permitAll()

                // 🔓 CATÁLOGOS PÚBLICOS
                .requestMatchers("/api/regiones/**").permitAll()
                .requestMatchers("/api/comunas/**").permitAll()
                .requestMatchers("/api/roles/**").permitAll()
                .requestMatchers("/api/productos/**").permitAll()


                .anyRequest().authenticated()
            )

            // ✅ AGREGAR EL FILTRO JWT (ESTE ERA TU ERROR)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ ENCRIPTAR CONTRASEÑAS
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ MANEJO DE AUTENTICACIÓN
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
