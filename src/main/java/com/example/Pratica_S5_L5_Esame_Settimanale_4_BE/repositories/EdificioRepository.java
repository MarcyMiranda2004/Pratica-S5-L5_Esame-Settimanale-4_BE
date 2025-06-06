package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    Edificio findByName(String name);
}
