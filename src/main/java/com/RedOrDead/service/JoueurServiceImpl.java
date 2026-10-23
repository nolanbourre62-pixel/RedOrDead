package com.RedOrDead.service;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.repository.JoueurRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JoueurServiceImpl implements JoueurService {
    private final JoueurRepository repository;
    public JoueurServiceImpl(JoueurRepository repository) { this.repository = repository; }
    public List<Joueur> trouverTous() { return repository.findAll(); }
    public Joueur trouverParId(Long id) { return repository.findById(id).orElse(null); }
    public Joueur creer(Joueur joueur) { return repository.save(joueur); }
    public Joueur modifier(Long id, Joueur joueur) {
        joueur.setId(id);
        return repository.save(joueur);
    }
    public void supprimer(Long id) { repository.deleteById(id); }
}
