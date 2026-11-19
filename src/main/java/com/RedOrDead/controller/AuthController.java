package com.RedOrDead.controller;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.repository.JoueurRepository;
import com.RedOrDead.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JoueurRepository joueurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(JoueurRepository joueurRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.joueurRepository = joueurRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Joueur joueur) {
        if (joueurRepository.findByPseudo(joueur.getPseudo()).isPresent()) {
            return ResponseEntity.badRequest().body("Ce pseudo est déjà utilisé.");
        }
        joueur.setPassword(passwordEncoder.encode(joueur.getPassword()));
        Joueur saved = joueurRepository.save(joueur);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Joueur request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword())
        );
        String token = jwtService.generateToken(request.getPseudo());
        return ResponseEntity.ok(Map.of("token", token));
    }
}