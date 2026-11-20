package com.RedOrDead.service;

import com.RedOrDead.entity.*;
import com.RedOrDead.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service

public class PartieServiceImpl implements PartieService {

    private final PartieRepository parties;
    private final JoueurRepository joueurs;
    public PartieServiceImpl(PartieRepository p, JoueurRepository j) {
        parties = p; joueurs = j;
    }
    public List<Partie> trouverToutes() {
        return parties.findAll();
    }
    public Partie trouverParId(Long id) {
        return parties.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partie introuvable"));
    }

    public Partie creer(Long joueurId) {

        Joueur joueur = joueurs.findById(joueurId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable"));
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
        if (!parties.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partie introuvable");
        }
        partie.setId(id);
        return parties.save(partie);
    }

    public void supprimer(Long id) {
        if (!parties.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partie introuvable");
        }
        parties.deleteById(id);
    }

    @Override
    public List<Partie> trouverParJoueur(Long joueurId) {
        return parties.findByJoueurId(joueurId);
    }

    @Override
    public Partie creerParPseudo(String pseudo) {
        Joueur joueur = joueurs.findByPseudo(pseudo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable"));
        int n = (int) (Math.random() * 37);
        Partie p = new Partie();
        p.setJoueur(joueur);
        p.setNumeroGagnant(n);
        p.setCouleurGagnante(n == 0 ? "VERT" : estRouge(n) ? "ROUGE" : "NOIR");
        return parties.save(p);
    }
}