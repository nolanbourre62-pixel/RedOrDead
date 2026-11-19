package com.RedOrDead.controller;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.repository.JoueurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JoueurRepository joueurRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JoueurRepository joueurRepository, PasswordEncoder passwordEncoder) {
        this.joueurRepository = joueurRepository;
        this.passwordEncoder = passwordEncoder;
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
}