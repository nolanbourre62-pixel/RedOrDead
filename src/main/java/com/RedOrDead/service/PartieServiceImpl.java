package com.RedOrDead.service;

import com.RedOrDead.entity.*;
import com.RedOrDead.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartieServiceImpl implements PartieService {
    private final PartieRepository parties;
    private final JoueurRepository joueurs;
    public PartieServiceImpl(PartieRepository p, JoueurRepository j) { parties = p; joueurs = j; }
    public List<Partie> trouverToutes() { return parties.findAll(); }
    public Partie trouverParId(Long id) { return parties.findById(id).orElse(null); }

    public Partie creer(Long joueurId) {
        Joueur joueur = joueurs.findById(joueurId).orElse(null);
        if (joueur == null) return null;
        int n = (int) (Math.random() * 37);
        Partie p = new Partie();
        p.setJoueur(joueur);
        p.setNumeroGagnant(n);
        p.setCouleurGagnante(n == 0 ? "VERT" : estRouge(n) ? "ROUGE" : "NOIR");
        return parties.save(p);
    }

    private boolean estRouge(int n) {
        return switch (n) {
            case 1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36 -> true;
            default -> false;
        };
    }
    public Partie modifier(Long id, Partie partie) {
        partie.setId(id);
        return parties.save(partie);
    }
    public void supprimer(Long id) { parties.deleteById(id); }
}
