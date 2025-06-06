package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.enumereted.TipoPostazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;  // importa ToString

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postazioni")
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postazioneId;

    @Column(nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPostazione tipoPostazione;

    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "edificio_id", nullable = false)
    @ToString.Exclude
    private Edificio edificio;
}
