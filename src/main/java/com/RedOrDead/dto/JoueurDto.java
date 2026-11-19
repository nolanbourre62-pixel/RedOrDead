package com.RedOrDead.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoueurDto {

    @NotBlank(message = "Le pseudo du joueur est obligatoire")
    private String pseudo;
}