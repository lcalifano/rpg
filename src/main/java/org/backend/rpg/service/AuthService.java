package org.backend.rpg.service;

import lombok.RequiredArgsConstructor;
import org.backend.rpg.dto.AuthResponse;
import org.backend.rpg.dto.LoginRequest;
import org.backend.rpg.dto.RegisterRequest;
import org.backend.rpg.entity.Role;
import org.backend.rpg.entity.User;
import org.backend.rpg.exception.UserAlreadyExistsException;
import org.backend.rpg.exception.UserNotFoundException;
import org.backend.rpg.repository.RoleRepository;
import org.backend.rpg.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Verifica se l'utente esiste già
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username già utilizzato: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email già utilizzata: " + request.getEmail());
        }

        // Ottieni il ruolo USER di default
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .name("ROLE_USER")
                                .build()
                ));

        // Crea un nuovo utente
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new ArrayList<>(List.of(userRole)))
                .build();

        userRepository.save(user);

        // Genera il token JWT
        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .message("Registrazione completata con successo")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        try {
            // Autentica l'utente
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

            // Genera il token JWT
            String token = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .message("Login effettuato con successo")
                    .build();
        } catch (Exception e) {
            throw new UserNotFoundException("Credenziali non valide");
        }
    }
}