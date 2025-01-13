package com.restaurant.plazoleta.infraestructure.input.rest;

import com.restaurant.plazoleta.application.handler.IUserHandler;
import com.restaurant.plazoleta.infraestructure.security.AuthManager;
import com.restaurant.plazoleta.infraestructure.security.CustomUserDetails;
import com.restaurant.plazoleta.infraestructure.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserHandler userHandler;
    private final AuthManager authManager;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        // Obtener la cabecera de autorización
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            // Decodificar las credenciales en base64
            String base64Credentials = authorizationHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);

            String email = values[0];
            String password = values[1];

            try {
                // Intentar autenticar con las credenciales proporcionadas
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password)
                );
            } catch (BadCredentialsException e) {
                // Si las credenciales son incorrectas
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            // Cargar los detalles del usuario
            final CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(email);

            // Si el usuario es una instancia de CustomUserDetails
            if (customUserDetails != null) {
                // Generar el JWT usando el JwtService
                final String jwt = jwtService.generateTokenWithUserInfo(customUserDetails);

                // Retornar el token JWT generado
                return ResponseEntity.ok(jwt);
            } else {
                // Si la instancia de UserDetails no es la esperada
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserDetails instance is invalid");
            }
        } else {
            // Si la cabecera de autorización está mal o falta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header missing or incorrect");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getUserById(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id) {
        try {
            // Validar el token
            jwtService.extractClaims(token.substring(7));

            // Obtener información del usuario a través del UserHandler
            Long userResponse = userHandler.getUserById(id);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            // Token inválido o cualquier otro error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
