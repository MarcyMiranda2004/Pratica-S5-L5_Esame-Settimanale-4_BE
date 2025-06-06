package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.configuration;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Edificio;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Postazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.enumereted.TipoPostazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.EdificioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.Pratica_S5_L5_Esame_Settimanale_4_BE")
public class AppConfig {

    //Edifici
    @Bean("edificio1")
    Edificio edificio1() {
        Edificio edificio1 = new Edificio();
        edificio1.setName("Sede A");
        edificio1.setAddress("Via Roma 1");
        edificio1.setCity("Milano");
        return edificio1;
    }

    @Bean("edificio2")
    Edificio edificio2() {
        Edificio edificio2 = new Edificio();
        edificio2.setName("Sede B");
        edificio2.setAddress("Corso Italia 1");
        edificio2.setCity("Napoli");
        return edificio2;
    }

    @Bean("edificio3")
    Edificio edificio3() {
        Edificio edificio3 = new Edificio();
        edificio3.setName("Sede C");
        edificio3.setAddress("Piazza Garibaldi 1");
        edificio3.setCity("Roma");
        return edificio3;
    }

    //Postazioni
    @Bean("postazione1")
    Postazione postazione1() {
        Postazione postazione1 = new Postazione();
        postazione1.setDescrizione("Sala riunioni del 7° piano zona A");
        postazione1.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
        postazione1.setPostiDisponibili(10);
        postazione1.setEdificio(edificio1());
        return postazione1;
    }

    @Bean("postazione2")
    Postazione postazione2() {
        Postazione postazione2 = new Postazione();
        postazione2.setDescrizione("Sala Openspace del 5° piano zona B");
        postazione2.setTipoPostazione(TipoPostazione.OPENSPACE);
        postazione2.setPostiDisponibili(6);
        postazione2.setEdificio(edificio2());
        return postazione2;
    }

    @Bean("postazione3")
    Postazione postazione3() {
        Postazione postazione3 = new Postazione();
        postazione3.setDescrizione("Sala Privata del 3° piano zona C");
        postazione3.setTipoPostazione(TipoPostazione.PRIVATO);
        postazione3.setPostiDisponibili(6);
        postazione3.setEdificio(edificio3());
        return postazione3;
    }
}
