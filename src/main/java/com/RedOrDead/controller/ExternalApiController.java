package com.RedOrDead.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/externe")
public class ExternalApiController {

    private final RestClient restClient = RestClient.create();

    @GetMapping("/conseil")
    public String getConseilDuJoueur() {
        return restClient.get()
                .uri("https://api.adviceslip.com/advice")
                .retrieve()
                .body(String.class);
    }
}