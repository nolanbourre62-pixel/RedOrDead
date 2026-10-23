package com.RedOrDead.entity;

import jakarta.persistence.*;

@Entity
public class Partie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroGagnant;
    private String couleurGagnante;

    @ManyToOne
    private Joueur joueur;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumeroGagnant() { return numeroGagnant; }
    public void setNumeroGagnant(int n) { numeroGagnant = n; }
    public String getCouleurGagnante() { return couleurGagnante; }
    public void setCouleurGagnante(String c) { couleurGagnante = c; }
    public Joueur getJoueur() { return joueur; }
    public void setJoueur(Joueur j) { joueur = j; }
}
