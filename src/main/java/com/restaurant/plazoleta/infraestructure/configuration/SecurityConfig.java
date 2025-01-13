package com.restaurant.plazoleta.infraestructure.configuration;

import com.restaurant.plazoleta.infraestructure.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configuración sin estado
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/restaurantes/**").permitAll()// Permitir acceso público a los endpoints de autenticación
                        .requestMatchers("/admin/**").permitAll() // Solo accesible para usuarios con rol ADMIN
                        .requestMatchers("/owner/**").hasRole("ADMIN") // Solo accesible para usuarios con rol OWNER
                        .requestMatchers("/employee/**").hasRole("OWNER") // Solo accesible para usuarios con rol EMPLOYEE
                        .requestMatchers("/client/**").permitAll() // Solo accesible para usuarios con rol CLIENT
                        .anyRequest().authenticated() // Todos los demás endpoints requieren autenticación
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Añadir el filtro JWT antes del filtro estándar

        return http.build();
    }
*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita CSRF para simplificar (útil en APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las solicitudes, autenticadas o no
                )
                .httpBasic().disable() // Opcional: Deshabilita autenticación básica
                .formLogin().disable(); // Opcional: Deshabilita formula

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}