package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;  // importa ToString

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prenotazioneId;

    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postazione_id", nullable = false)
    @ToString.Exclude
    private Postazione postazione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id", nullable = false)
    @ToString.Exclude
    private Utente utente;
}
