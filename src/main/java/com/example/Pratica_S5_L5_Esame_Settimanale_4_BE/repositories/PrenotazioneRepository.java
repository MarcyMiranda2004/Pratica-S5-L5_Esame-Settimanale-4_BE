package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Prenotazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.enumereted.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByUtente_UtenteId(Long utenteId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Prenotazione p WHERE p.postazione.id = :postazioneId AND p.dataPrenotazione = :dataPrenotazione")
    boolean existsByPostazioneIdAndData(@Param("postazioneId") Long postazioneId, @Param("dataPrenotazione") LocalDate dataPrenotazione);

    // Query inutilizzate
    // List<Prenotazione> findByDataPrenotazione(LocalDate date);
    // List<Prenotazione> findByPostazione_PostazioneId(Long postazioneId);
    // List<Prenotazione> findByPostazione_TipoPostazione(TipoPostazione tipoPostazione);
    // List<Prenotazione> findByPostazione_Edificio_City(String city);
}
