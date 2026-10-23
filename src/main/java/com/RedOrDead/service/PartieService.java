package com.RedOrDead.service;

import com.RedOrDead.entity.Partie;
import java.util.List;

public interface PartieService {
    List<Partie> trouverToutes();
    Partie trouverParId(Long id);
    Partie creer(Long joueurId);
    Partie modifier(Long id, Partie partie);
    void supprimer(Long id);
}
