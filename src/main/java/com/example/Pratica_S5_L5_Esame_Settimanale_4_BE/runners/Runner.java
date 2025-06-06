package com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.runners;

import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Postazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Prenotazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.entities.Utente;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.enumereted.TipoPostazione;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.EdificioRepository;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.PostazioneRepository;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.PrenotazioneRepository;
import com.example.Pratica_S5_L5_Esame_Settimanale_4_BE.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private EdificioRepository edificioRepository;
    @Autowired
    private PostazioneRepository postazioneRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Registrazione");
            System.out.println("2 - Login");
            System.out.println("0 - Esci");
            System.out.print("Scegli un'opzione: ");

            String scelta = scan.nextLine();

            // Menu Log in e Reggistrazione
            switch (scelta) {
                case "1":
                    registrazioneUtente(scan);
                    break;
                case "2":
                    loginUtente(scan);
                    break;
                case "0":
                    System.out.println("Uscita dal programma...");
                    running = false;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;
            }
        }
        scan.close();
    }

    // Metodo per la reggistrazione Utente
    private void registrazioneUtente(Scanner scan) {
        try {
            System.out.print("Inserisci il tuo Nome: ");
            String name = scan.nextLine();

            System.out.print("Inserisci il tuo Cognome: ");
            String surname = scan.nextLine();

            System.out.print("Inserisci la tua Email: ");
            String email = scan.nextLine();

            if (utenteRepository.findByEmail(email) != null) {
                System.out.println("Errore: Email già registrata.");
                return;
            }

            System.out.print("Crea un Username: ");
            String username = scan.nextLine();

            if (utenteRepository.findByUsername(username) != null) {
                System.out.println("Errore: Username già esistente.");
                return;
            }

            System.out.print("Crea una Password: ");
            String password = scan.nextLine();

            Utente u = new Utente();
            u.setName(name);
            u.setSurname(surname);
            u.setEmail(email);
            u.setUsername(username);
            u.setPassword(password);

            utenteRepository.save(u);
            System.out.println("Registrazione avvenuta con successo.");

        } catch (Exception e) {
            System.out.println("Errore durante la registrazione: " + e.getMessage());
        }
    }

    // Metodo per il login Utente
    private void loginUtente(Scanner scan) {
        System.out.print("Inserisci Username: ");
        String username = scan.nextLine();

        System.out.print("Inserisci Password: ");
        String password = scan.nextLine();

        Utente u = utenteRepository.findByUsername(username);

        if (u != null && u.getPassword().equals(password)) {
            System.out.println("Login effettuato con successo.");
            menuPostLogin(scan, u);
        } else {
            System.out.println("Username o password errati.");
        }
    }


    private void menuPostLogin(Scanner scan, Utente utente) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nChe operazione vuoi svolgere ?");
            System.out.println("1. Visualizza postazioni disponibili in una determinata data");
            System.out.println("2. Visualizza prenotazioni effettuate");
            System.out.println("3. Prenota una postazione");
            System.out.println("4. Cerca una postazione tramite tipo e città di interesse");
            System.out.println("0. Logout");
            System.out.print("Inserisci la tua scelta: ");

            String sceltaOperazione = scan.nextLine();

            switch (sceltaOperazione) {
                case "1":
                    visualizzaPostazioniDisponibili(scan);
                    break;

                case "2":
                    visualizzaPrenotazioniUtente(utente);
                    break;

                case "3":
                    prenotaPostazione(scan, utente);
                    break;

                case "4":
                    cercaPostazioneTipoECitta(scan);
                    break;

                case "0":
                    System.out.println("Logout effettuato.");
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    // 1. Metodo per visualizzare le postazioni disponibili in una data specifica
    private void visualizzaPostazioniDisponibili(Scanner scan) {
        try {
            System.out.print("Inserisci la data (formato YYYY-MM-DD): ");
            LocalDate data = LocalDate.parse(scan.nextLine());

            List<Postazione> disponibili = postazioneRepository.findDisponibiliInData(data);
            if (disponibili.isEmpty()) {
                System.out.println("Nessuna postazione disponibile per la data indicata.");
            } else {
                System.out.println("Postazioni disponibili per il " + data + ":");
                for (Postazione p : disponibili) {
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Data non valida o errore nella ricerca.");
        }
    }

    // 2. Metodo per visualizzare le prenotazioni dell'utente
    private void visualizzaPrenotazioniUtente(Utente utente) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtente_UtenteId(utente.getUtenteId());
        if (prenotazioni.isEmpty()) {
            System.out.println("Non hai prenotazioni effettuate.");
        } else {
            System.out.println("Le tue prenotazioni:");
            for (Prenotazione p : prenotazioni) {
                System.out.println(p);
            }
        }
    }

    // 3. Metodo per prenotare una postazione
    private void prenotaPostazione(Scanner scan, Utente utente) {
        try {
            System.out.print("Data prenotazione (formato YYYY-MM-DD): ");
            LocalDate dataPrenotazione = LocalDate.parse(scan.nextLine());

            System.out.print("Città desiderata: ");
            String cittaPrenotazione = scan.nextLine();

            System.out.print("Edificio desiderato: ");
            String edificioPrenotazione = scan.nextLine();

            System.out.println("Tipo di postazione:");
            System.out.println("1. Privata");
            System.out.println("2. Openspace");
            System.out.println("3. Sala Riunioni");
            System.out.print("Scelta: ");
            String tipoScelta = scan.nextLine();

            TipoPostazione tipoPostazione;
            switch (tipoScelta) {
                case "1":
                    tipoPostazione = TipoPostazione.PRIVATO;
                    break;
                case "2":
                    tipoPostazione = TipoPostazione.OPENSPACE;
                    break;
                case "3":
                    tipoPostazione = TipoPostazione.SALA_RIUNIONI;
                    break;
                default:
                    System.out.println("Tipo postazione non valido.");
                    return;
            }

            List<Postazione> candidate = postazioneRepository.findByTipoPostazioneAndEdificio_City(tipoPostazione, cittaPrenotazione);

            candidate.removeIf(p -> prenotazioneRepository.existsByPostazioneIdAndData(p.getPostazioneId(), dataPrenotazione));

            if (candidate.isEmpty()) {
                System.out.println("Nessuna postazione disponibile per i criteri selezionati.");
                return;
            }

            System.out.println("Postazioni disponibili:");
            for (int i = 0; i < candidate.size(); i++) {
                System.out.println((i + 1) + ". " + candidate.get(i));
            }

            System.out.print("Seleziona il numero della postazione da prenotare: ");
            int scelta = Integer.parseInt(scan.nextLine());

            if (scelta < 1 || scelta > candidate.size()) {
                System.out.println("Scelta non valida.");
                return;
            }

            Postazione postazioneScelta = candidate.get(scelta - 1);

            Prenotazione nuovaPrenotazione = new Prenotazione();
            nuovaPrenotazione.setDataPrenotazione(dataPrenotazione);
            nuovaPrenotazione.setPostazione(postazioneScelta);
            nuovaPrenotazione.setUtente(utente);

            prenotazioneRepository.save(nuovaPrenotazione);

            System.out.println("Prenotazione effettuata con successo!");

        } catch (Exception e) {
            System.out.println("Errore nella prenotazione: " + e.getMessage());
        }
    }

    // 4. Metodo per cercare una postazione tramite tipo e città
    private void cercaPostazioneTipoECitta(Scanner scan) {
        try {
            System.out.println("Tipo postazione:");
            System.out.println("1. Privata");
            System.out.println("2. Openspace");
            System.out.println("3. Sala Riunioni");
            System.out.print("Scelta: ");
            String tipoScelta = scan.nextLine();

            TipoPostazione tipoPostazione;
            switch (tipoScelta) {
                case "1":
                    tipoPostazione = TipoPostazione.PRIVATO;
                    break;
                case "2":
                    tipoPostazione = TipoPostazione.OPENSPACE;
                    break;
                case "3":
                    tipoPostazione = TipoPostazione.SALA_RIUNIONI;
                    break;
                default:
                    System.out.println("Tipo postazione non valido.");
                    return;
            }

            System.out.print("Inserisci la città di interesse: ");
            String citta = scan.nextLine();

            List<Postazione> risultati = postazioneRepository.findByTipoPostazioneAndEdificio_City(tipoPostazione, citta);

            if (risultati.isEmpty()) {
                System.out.println("Nessuna postazione trovata per i criteri inseriti.");
            } else {
                System.out.println("Postazioni trovate:");
                for (Postazione p : risultati) {
                    System.out.println(p);
                }
            }

        } catch (Exception e) {
            System.out.println("Errore nella ricerca: " + e.getMessage());
        }
    }
}
