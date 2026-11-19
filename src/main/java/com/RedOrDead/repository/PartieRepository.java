package com.RedOrDead.repository;

import com.RedOrDead.entity.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartieRepository extends JpaRepository<Partie, Long> {

    List<Partie> findByJoueurId(Long joueurId);

    List<Partie> findByCouleurGagnante(String couleurGagnante);
}