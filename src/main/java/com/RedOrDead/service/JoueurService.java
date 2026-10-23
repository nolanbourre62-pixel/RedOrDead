package com.RedOrDead.service;

import com.RedOrDead.entity.Joueur;
import java.util.List;

public interface JoueurService {
    List<Joueur> trouverTous();
    Joueur trouverParId(Long id);
    Joueur creer(Joueur joueur);
    Joueur modifier(Long id, Joueur joueur);
    void supprimer(Long id);
}
