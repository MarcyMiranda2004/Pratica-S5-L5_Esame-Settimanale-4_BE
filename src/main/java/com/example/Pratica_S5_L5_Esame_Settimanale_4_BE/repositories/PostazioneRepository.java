package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Postazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.enumereted.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    List<Postazione> findByTipoPostazioneAndEdificio_City(TipoPostazione tipoPostazione, String city);

    @Query("SELECT p FROM Postazione p WHERE p.postazioneId NOT IN (SELECT pr.postazione.postazioneId FROM Prenotazione pr WHERE pr.dataPrenotazione = :data)")
    List<Postazione> findDisponibiliInData(@Param("data") LocalDate data);

    // Query inutilizzate
    // List<Postazione> findByTipoPostazione(TipoPostazione tipoPostazione);
    // List<Postazione> findByEdificioCity(String city);
}
