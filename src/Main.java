import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Leggi i dati degli insegnamenti e degli studenti da file al lancio del
        // programma
        InsegnamentoController.leggiDati();
        StudenteController.leggiDati();

        // Messaggio di benvenuto
        System.out.printf("Benvenuti!%n");

        // Visualizza il menu principale
        stampiMenuPrincipale();

        // Chiudi il programma quando il menu restituisce il comando di uscita
        esciProgramma();
    }

    /**
     * Visualizza il menu principale del programma.
     */
    public static void stampiMenuPrincipale() {
        System.out.printf("Menu principale:%n" +
                "1 - Effettua l'accesso come amministratore/trice%n" +
                "2 - Effettua l'accesso come studente/ssa%n" +
                "3 - Chiudi il programma%n" +
                "Scegli un'opzione, per favore: ");

        // Ottieni un'opzione valida dall'utente (1, 2 o 3)
        int opzione = opzioneValida(3);

        // Stampa una linea orizzontale per separare le sezioni del menu
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Menu per l'accesso come amministratore/trice
        if (opzione == 1)
            adminLogin();

            // Menu per l'accesso come studente/ssa
        else if (opzione == 2)
            studenteLogin();

            // Se l'opzione è 3, esce dal programma
        else
            return;

        // Dopo aver gestito l'opzione, torna al menu principale
        stampiMenuPrincipale();
    }

    /**
     * Gestisce l'accesso dell'amministratore/trice al sistema.
     * Richiede l'inserimento del nome utente e della password.
     * Consente all'utente di riprovare in caso di nome utente o password errati.
     * Avvia le operazioni amministrative se l'accesso avviene con successo.
     */
    public static void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nome utente dell'amministratore/trice : ");
        String idStudente = scanner.nextLine();
        System.out.print("Inserisci la password dell'amministratore/trice : ");
        String password = scanner.nextLine();
        char response = 's';

        // Continua a chiedere nome utente e password fino a un massimo di 'n' tentativi
        // o accesso riuscito
        while (response == 's' && (!idStudente.equals("admin") || !password.equals("123"))) {
            System.out.print("Nome utente o password errati! Vuoi riprovare? s o n : ");
            response = rispostaValida();
            if (response == 's') {
                System.out.print("Inserisci il nome utente dell'amministratore/trice : ");
                idStudente = scanner.nextLine();
                System.out.print("Inserisci la password dell'amministratore/trice : ");
                password = scanner.nextLine();
            }
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Se l'accesso è riuscito, avvia le operazioni amministrative
        if (response == 's') {
            System.out.printf("Hai effettuato l'accesso con successo come admin!%n");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------------");
            adminOperazioni();
        }
    }

    /**
     * Gestisce le operazioni amministrative dopo che l'amministratore/trice ha
     * effettuato l'accesso.
     * Fornisce un menu con opzioni per l'amministrazione degli studenti, degli insegnamenti,
     * la terminazione della sessione o la chiusura del programma.
     * Richiede all'utente di selezionare un'opzione valida e avvia l'azione
     * corrispondente.
     */
    public static void adminOperazioni() {
        System.out.printf("Operazioni amministrative:%n" +
                "1 - Amministrazione degli studenti%n" +
                "2 - Amministrazione degli insegnamenti%n" +
                "3 - Termina la sessione%n" +
                "4 - Chiudi il programma%n" +
                "Scegli un'opzione, per favore: ");

        int opzione = opzioneValida(4);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Gestione degli studenti
        if (opzione == 1)
            adminAmministraStudenti();

            // Gestione dei insegnamenti
        else if (opzione == 2)
            adminAmministraInsegnamenti();

            // Termina la sessione
        else if (opzione == 3)
            return;

            // Salva i dati ed esce dal programma
        else if (opzione == 4)
            esciProgramma();

        // Ritorna al menu delle operazioni amministrative
        adminOperazioni();
    }

    /**
     * Gestisce le operazioni di amministrazione degli insegnamenti.
     * Fornisce un menu con opzioni per aggiungere un nuovo corso, eliminare un
     * corso esistente, modificare un corso,
     * visualizzare un resoconto di tutti gli insegnamenti, visualizzare gli studenti
     * iscritti a un corso specifico o tornare indietro.
     * Richiede all'utente di selezionare un'opzione valida e avvia l'azione
     * corrispondente.
     */
    public static void adminAmministraInsegnamenti() {
        System.out.printf("Gestione Insegnamenti :%n" +
                "1 - Aggiungi un nuovo insegnamento%n" +
                "2 - Elimina un insegnamento%n" +
                "3 - Modifica un insegnamento%n" +
                "4 - Visualizza il resoconto di tutti gli insegnamenti%n" +
                "5 - Visualizza gli studenti iscritti a un insegnamento specifico%n" +
                "6 - Torni indietro%n" +
                "Scegli un'opzione, per favore: ");

        int courseOp = opzioneValida(6);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        if (courseOp == 1)
            InsegnamentoController.aggiungiInsegnamento();

        else if (courseOp == 2)
            InsegnamentoController.rimuoviInsegnamento(
                    InsegnamentoController.selezioniInsegnamento(InsegnamentoController.getInsegnamenti(), null));

        else if (courseOp == 3) {
            Insegnamento selectedInsegnamento = InsegnamentoController
                    .selezioniInsegnamento(InsegnamentoController.getInsegnamenti(), null);
            if (selectedInsegnamento != null)
                selectedInsegnamento.modifichiInsegnamento();
        }

        else if (courseOp == 4)
            InsegnamentoController.getDettagliInsegnamenti(InsegnamentoController.getInsegnamenti());

        else if (courseOp == 5) {
            Insegnamento selectedInsegnamento = InsegnamentoController
                    .selezioniInsegnamento(InsegnamentoController.getInsegnamenti(), null);
            if (selectedInsegnamento != null)
                selectedInsegnamento.visualizziStudentiIscritti();
        }

        else if (courseOp == 6)
            return;

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        adminAmministraInsegnamenti();
    }

    /**
     * Gestisce le operazioni di amministrazione degli studenti.
     * Fornisce un menu con opzioni per aggiungere uno nuovo studente, eliminare uno
     * studente esistente,
     * modificare uno studente, visualizzare tutti gli studenti, visualizzare il
     * resoconto di uno studente specifico o tornare indietro.
     * Richiede all'utente di selezionare un'opzione valida e avvia l'azione
     * corrispondente.
     */
    public static void adminAmministraStudenti() {
        System.out.printf("Amministrazione degli studenti : %n" +
                "1 - Inserisci un/a nuovo/a studente/ssa%n" +
                "2 - Elimina uno/una studente/ssa%n" +
                "3 - Modifica uno/una studente/ssa%n" +
                "4 - Visualizza tutti gli studenti%n" +
                "5 - Visualizza il resoconto di uno/una studente/ssa specifico/a%n" +
                "6 - Torni indietro%n" +
                "Scegli un'opzione, per favore: ");

        int studentOp = opzioneValida(6);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Aggiungi nuovo studente
        if (studentOp == 1)
            StudenteController.aggiungiStudente();

            // Elimina uno studente
        else if (studentOp == 2)
            StudenteController.eliminiStudente(StudenteController.selezioniStudente(StudenteController.getStudenti()));

            // Modifica uno studente
        else if (studentOp == 3) {
            Studente studenteSelezionato = StudenteController.selezioniStudente(StudenteController.getStudenti());
            if (studenteSelezionato != null)
                studenteSelezionato.modifichiStudente();
        }

        // Visualizza tutti gli studenti
        else if (studentOp == 4)
            StudenteController.stampiDatiStudente(StudenteController.getStudenti());

            // Visualizza il resoconto di uno studente specifico
        else if (studentOp == 5) {
            Studente studenteSelezionato = StudenteController.selezioniStudente(StudenteController.getStudenti());
            if (studenteSelezionato != null)
                studenteSelezionato.stampiResocontoCompleto();
        }

        else if (studentOp == 6)
            return;

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        adminAmministraStudenti();
    }

    /**
     * Gestisce l'accesso per gli studenti.
     * Richiede all'utente di inserire l'ID studente e la password, verifica le
     * credenziali
     * e permette l'accesso se sono corrette. Successivamente, avvia le operazioni
     * studentesche.
     */
    public static void studenteLogin() {
        if (StudenteController.getStudenti().size() == 0) {
            System.out.printf(
                    "Non ci sono studenti!%nDevi effettuare l'accesso come amministratore/trice prima per aggiungere studenti.%n");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci l'ID studente dello/a studente/ssa: ");
        String idStudente = scanner.nextLine();
        System.out.print("Inserisci la password dello/a studente/ssa: ");
        String password = scanner.nextLine();
        Studente studente1 = StudenteController.login(idStudente, password);
        char response = 's';
        while (response == 's' && studente1 == null) {
            System.out.print("Nome utente o password errati! Vuoi riprovare? s o n : ");
            response = rispostaValida();
            if (response == 's') {
                System.out.print("Inserisci l'ID studente dello/a studente/ssa: ");
                idStudente = scanner.nextLine();
                System.out.print("Inserisci la password dello/a studente/ssa: ");
                password = scanner.nextLine();
                studente1 = StudenteController.login(idStudente, password);
            }
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (response == 's') {
            System.out.printf("Hai effettuato l'accesso con successo come %s %s!%n", studente1.getNome(),
                    studente1.getCognome());
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------------------");
            studenteOperazioni(studente1);
        }
    }

    /**
     * Gestisce le operazioni disponibili per gli studenti autenticati.
     * Fornisce un menu con opzioni per visualizzare i resoconti dello studente,
     * gestire i propri dati,
     * terminare la sessione o chiudere il programma. Richiede all'utente di
     * selezionare un'opzione valida
     * e avvia l'azione corrispondente.
     *
     * @param studente1 Lo studente autenticato.
     */
    public static void studenteOperazioni(Studente studente1) {
        System.out.printf("Operazioni studentesche:%n" +
                "1 - Visualizza i tuoi resoconti%n" +
                "2 - Gestisci i tuoi dati%n" +
                "3 - Termina la sessione%n" +
                "4 - Chiudi il programma%n" +
                "Scegli un'opzione, per favore: ");


        int opzione = opzioneValida(4);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        if (opzione == 1)
            visualizziStudenteResoconti(studente1);

        else if (opzione == 2)
            studenteAmministraSuoiDati(studente1);

            // Termina la sessione
        else if (opzione == 3)
            return;

            // Termina la sessione e salva i dati
        else if (opzione == 4)
            esciProgramma();

        studenteOperazioni(studente1);
    }

    /**
     * Gestisce le operazioni relative ai resoconti dello studente.
     * Fornisce un menu con opzioni per visualizzare gli insegnamenti a cui lo studente è
     * iscritto,
     * gli insegnamenti disponibili per l'iscrizione, il resoconto completo dello studente o
     * tornare indietro.
     * Richiede all'utente di selezionare un'opzione valida e avvia l'azione
     * corrispondente.
     *
     * @param studente1 Lo studente autenticato.
     */
    public static void visualizziStudenteResoconti(Studente studente1) {
        System.out.printf("Resoconti disponibili:%n" +
                "1 - Visualizza tutti gli insegnamenti iscritti%n" +
                "2 - Visualizza tutti gli insegnamenti disponibili per l'iscrizione%n" +
                "3 - Visualizza il resoconto completo%n" +
                "4 - Torni indietro%n" +
                "Scegli un'opzione, per favore: ");

        int opzione = opzioneValida(4);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Visualizza tutti gli insegnamenti iscritti
        if (opzione == 1)
            studente1.stampiStudenteInsegnamenti(studente1.getInsegnamentiIscriti());

            // Visualizza tutti gli insegnamenti disponibili
        else if (opzione == 2) {
            ArrayList<Insegnamento> available = studente1.getInsegnamentiDisponibili();
            if (available != null)
                InsegnamentoController.getDettagliInsegnamenti(available);
        }

        // Visualizza il resoconto completo
        else if (opzione == 3)
            studente1.stampiResocontoCompleto();

        else if (opzione == 4)
            return;

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        visualizziStudenteResoconti(studente1);
    }

    /**
     * Gestisce le operazioni di gestione dei dati personali dello studente.
     * Fornisce un menu con opzioni per cambiare l'ID studente, la password,
     * iscriversi a un insegnamento, ritirarsi da un insegnamento o tornare
     * indietro.
     * Richiede all'utente di selezionare un'opzione valida e avvia l'azione
     * corrispondente.
     *
     * @param studente1 Lo studente autenticato.
     */
    public static void studenteAmministraSuoiDati(Studente studente1) {
        System.out.printf("Gestire dati:%n" +
                "1 - Cambia ID studente%n" +
                "2 - Modifica password%n" +
                "3 - Iscriviti a un'insegnamento%n" +
                "4 - Ritirati da un'insegnamento%n" +
                "5 - Torni indietro%n" +
                "Scegli un'opzione, per favore: ");

        int opzione = opzioneValida(5);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Cambia l'ID studente
        if (opzione == 1)
            studente1.aggiorniIdStudente();

            // Cambia la password
        else if (opzione == 2)
            studente1.aggiorniPassword();

            // Iscriviti a un insegnamento
        else if (opzione == 3) {
            if (studente1.getInsegnamentiDisponibili() != null)
                studente1.iscrivitiAInsegnamento(
                        InsegnamentoController.selezioniInsegnamento(studente1.getInsegnamentiDisponibili(), null),
                        false);
        }

        // Ritirati da un insegnamento
        else if (opzione == 4)
            studente1.ritiratiDaInsegnamento(
                    InsegnamentoController.selezioniInsegnamento(studente1.getInsegnamentiIscriti(), studente1), true);

        else if (opzione == 5)
            return;

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
        studenteAmministraSuoiDati(studente1);
    }

    /**
     * Verifica se l'opzione inserita è valida, garantendo che sia un numero intero
     * compreso tra 1 e il valore massimo specificato.
     * Restituisce l'opzione valida.
     *
     * @param massimo Il valore massimo consentito per l'opzione.
     * @return L'opzione valida.
     */
    public static int opzioneValida(int massimo) {
        int opzione = getNumeriInteriValidi();
        while (opzione <= 0 || opzione > massimo) {
            System.out.print("Per favore, inserisci un'opzione valida: ");
            opzione = getNumeriInteriValidi();
        }
        return opzione;
    }

    /**
     * Ottiene un numero intero valido dalla console.
     * Continua a richiedere un input fino a quando non viene inserito un numero
     * intero valido.
     *
     * @return Il numero intero valido inserito dall'utente.
     */
    public static int getNumeriInteriValidi() {
        int input = 0;
        boolean flag;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.print("Per favore, inserisci un numero valido: ");
                flag = true;
            }
        } while (flag);
        return input;
    }

    /**
     * Ottiene un numero decimale valido dalla console.
     * Continua a richiedere un input fino a quando non viene inserito un numero
     * decimale valido.
     *
     * @return Il numero decimale valido inserito dall'utente.
     */
    public static double getNumeriDecimaliValidi() {
        double input = 0;
        boolean flag;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextDouble();
                flag = false;
            } catch (Exception e) {
                System.out.print("Per favore, inserisci un numero valido: ");
                flag = true;
            }
        } while (flag);
        return input;
    }

    /**
     * Ottiene una risposta valida dalla console, che deve essere 's' o 'n'.
     * Continua a richiedere una risposta fino a quando non viene inserita 's' o
     * 'n'.
     *
     * @return La risposta valida ('s' o 'n') inserita dall'utente.
     */
    public static char rispostaValida() {
        Scanner scanner = new Scanner(System.in);
        char response = scanner.nextLine().charAt(0);
        while (response != 's' && response != 'n') {
            System.out.print("Per favore, inserisci s o n: ");
            response = scanner.nextLine().charAt(0);
        }
        return response;
    }

    /**
     * Chiude il programma e salva i dati prima di uscire.
     * Stampa un messaggio di uscita e termina l'esecuzione.
     */
    public static void esciProgramma() {
        System.out.printf("Grazie e ci si vede presto! :D%n");
        InsegnamentoController.memorizziDati();
        StudenteController.memorizziDati();
        System.out.print("Dati nuovi salvati!");
        System.exit(0);
    }
}