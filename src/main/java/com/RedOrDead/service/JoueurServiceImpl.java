package com.RedOrDead.service;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.repository.JoueurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class JoueurServiceImpl implements JoueurService {
    private final JoueurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public JoueurServiceImpl(JoueurRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Joueur> trouverTous() {
        return repository.findAll();
    }
    public Joueur trouverParId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable"));
    }
    public Joueur creer(Joueur joueur) {
        joueur.setPassword(passwordEncoder.encode(joueur.getPassword()));
        return repository.save(joueur);
    }
    public Joueur modifier(Long id, Joueur joueur) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable");
        }
        joueur.setId(id);
        joueur.setPassword(passwordEncoder.encode(joueur.getPassword()));
        return repository.save(joueur);
    }
    public void supprimer(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable");
        }
        repository.deleteById(id);
    }
}