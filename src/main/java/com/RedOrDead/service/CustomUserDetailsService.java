package com.RedOrDead.service;

import com.RedOrDead.entity.Joueur;
import com.RedOrDead.repository.JoueurRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JoueurRepository joueurRepository;

    public CustomUserDetailsService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Joueur joueur = joueurRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("Joueur non trouvé : " + pseudo));

        return User.builder()
                .username(joueur.getPseudo())
                .password(joueur.getPassword())
                .roles("USER")
                .build();
    }
}