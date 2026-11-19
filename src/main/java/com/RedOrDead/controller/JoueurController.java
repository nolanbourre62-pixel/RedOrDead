package com.RedOrDead.controller;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.service.JoueurService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/joueurs")
public class JoueurController {
    private final JoueurService service;
    public JoueurController(JoueurService service) {
        this.service = service;

    }
    @GetMapping public List<Joueur> tous() {
        return service.trouverTous();
    }

    @GetMapping("/{id}") public Joueur un(@PathVariable Long id) {
        return service.trouverParId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping public Joueur creer(@Valid @RequestBody Joueur joueur) {
        return service.creer(joueur);
    }

    @PutMapping("/{id}") public Joueur modifier(@PathVariable Long id, @Valid @RequestBody Joueur joueur) {
        return service.modifier(id, joueur);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}