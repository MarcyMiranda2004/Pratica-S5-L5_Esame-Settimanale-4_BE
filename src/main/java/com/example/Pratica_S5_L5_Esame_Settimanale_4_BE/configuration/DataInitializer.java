package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.configuration;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Edificio;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Postazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.EdificioRepository;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.PostazioneRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    @Qualifier("edificio1")
    private Edificio edificio1;

    @Autowired
    @Qualifier("edificio2")
    private Edificio edificio2;

    @Autowired
    @Qualifier("edificio3")
    private Edificio edificio3;

    @Autowired
    @Qualifier("postazione1")
    private Postazione postazione1;

    @Autowired
    @Qualifier("postazione2")
    private Postazione postazione2;

    @Autowired
    @Qualifier("postazione3")
    private Postazione postazione3;

    @PostConstruct
    public void init() {
        edificioRepository.save(edificio1);
        edificioRepository.save(edificio2);
        edificioRepository.save(edificio3);

        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);
    }
}
