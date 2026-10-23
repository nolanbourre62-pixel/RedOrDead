package com.RedOrDead.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Joueur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudo;

    @JsonIgnore
    @OneToMany(mappedBy = "joueur")
    private List<Partie> parties = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }
    public List<Partie> getParties() { return parties; }
    public void setParties(List<Partie> parties) { this.parties = parties; }
}
