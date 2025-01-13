package com.restaurant.plazoleta.infraestructure.security;

import com.restaurant.plazoleta.infraestructure.output.jpa.entity.UserEntity;
import com.restaurant.plazoleta.infraestructure.output.jpa.repository.IUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthManager implements AuthenticationManager {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        // Buscar el usuario en la base de datos por email
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        // Verificar que la contraseña coincida
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        // Generar el token JWT
        String token = generateToken(userEntity);

        // Si la autenticación es exitosa, se crea un nuevo UsernamePasswordAuthenticationToken con el token generado
        return new UsernamePasswordAuthenticationToken(email, token, authentication.getAuthorities());
    }

    // Método para generar el token JWT
    private String generateToken(UserEntity userEntity) {
        return Jwts.builder()
                .setSubject(userEntity.getEmail()) // El correo como sujeto
                .claim("role", userEntity.getIdRole().getId()) // El ID del rol
                .claim("lastName", userEntity.getLastName()) // El apellido
                .claim("email", userEntity.getEmail()) // El correo
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Token válido por 1 hora
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}