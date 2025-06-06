package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
    Utente findByEmail(String email);
}
