package com.RedOrDead.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartieDto {

    @NotNull(message = "L'identifiant du joueur est obligatoire")
    private Long joueurId;

    @NotNull(message = "La mise est obligatoire")
    @Positive(message = "La mise doit être strictement supérieure à 0")
    private Double mise;

    @NotBlank(message = "Le pari (ROUGE, NOIR ou un numéro entre 0 et 36) est obligatoire")
    private String pari;
}