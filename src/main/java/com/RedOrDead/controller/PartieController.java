package com.RedOrDead.controller;

import com.RedOrDead.entity.Partie;
import com.RedOrDead.service.PartieService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartieController {
    private final PartieService service;
    public PartieController(PartieService service) { this.service = service; }
    @GetMapping public List<Partie> toutes() { return service.trouverToutes(); }
    @GetMapping("/{id}") public Partie une(@PathVariable Long id) { return service.trouverParId(id); }
    @PostMapping("/jouer/{joueurId}") public Partie creer(@PathVariable Long joueurId) {
        return service.creer(joueurId);
    }
    @PutMapping("/{id}") public Partie modifier(@PathVariable Long id, @RequestBody Partie partie) {
        return service.modifier(id, partie);
    }
    @DeleteMapping("/{id}") public void supprimer(@PathVariable Long id) { service.supprimer(id); }
}
