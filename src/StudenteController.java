// Importazione delle classi necessarie per gestire file, collezioni e input/output
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * Questa classe gestisce la collezione di studenti e fornisce metodi per interagire con essa.
 * È una classe che funge da controller per la gestione degli oggetti di tipo Studente all'interno di un sistema o
 * programma.
 * La classe StudenteController contiene principalmente metodi statici per gestire gli oggetti Studente, come
 * la lettura dei dati da un file, l'aggiunta di nuovi studenti, l'eliminazione di studenti esistenti,
 * il login e altro ancora.
 */
public final class StudenteController {
    // ArrayList per archiviare gli oggetti Studente
    private static final ArrayList<Studente> studenti = new ArrayList<>();

    // HashSet per archiviare gli ID degli studenti per il controllo univoco
    private static final HashSet<String> listaIdUtenti = new HashSet<>();

    // Il costruttore privato impedisce l'istanziazione di oggetti di questa classe
    private StudenteController() {}

    /**
     * Questo metodo legge i dati degli studenti da un file di testo e li carica nell'applicazione.
     * I dati degli studenti sono memorizzati in un formato CSV, dove ciascuna riga rappresenta un singolo studente
     * con le informazioni separate da virgole.
     * @see Studente
     * @see InsegnamentoController#getInsegnamentoConId(String)
     */
    public static void leggiDati() {
        // Percorso del file contenente i dati degli studenti
        File file = new File("./src/Studenti.txt");

        try {
            // Creazione di uno scanner per leggere il file
            Scanner scanner = new Scanner(file);

            // Loop per leggere il file riga per riga
            while (scanner.hasNextLine()) {
                // Divisione dei dati dello studente in base alla virgola
                String[] datiStudente = scanner.nextLine().split(",");

                // Creazione di un nuovo oggetto Studente utilizzando i dati letti
                Studente studenteNuovo = new Studente(
                        datiStudente[0],   // ID studente
                        datiStudente[1],   // Nome
                        Integer.parseInt(datiStudente[2]), // Trimestre
                        datiStudente[3],   // Corso
                        datiStudente[4],   // Numero di telefono
                        datiStudente[5],   // Password
                        datiStudente[6],   // Cognome
                        Integer.parseInt(datiStudente[7]) // Età
                );

                // Aggiunta dell'ID studente all'insieme degli ID già utilizzati
                listaIdUtenti.add(datiStudente[0].toLowerCase());

                // Lettura degli insegnamenti a cui lo studente è iscritto
                HashMap<Insegnamento, Double> insegnamentiStudente = new HashMap<>();
                String[] splitInsegnamenti = scanner.nextLine().split(",");

                // Verifica se lo studente è iscritto a insegnamenti
                if (!splitInsegnamenti[0].equals("")) {
                    for (String insegnamento : splitInsegnamenti) {
                        // Divisione dei dati dell'insegnamento in base ai due punti (ID insegnamento : Voto)
                        String courseId = insegnamento.split(":")[0];
                        double voto = Double.parseDouble(insegnamento.split(":")[1]);

                        // Ottenimento dell'oggetto Insegnamento corrispondente all'ID
                        Insegnamento insegnamento1 = InsegnamentoController.getInsegnamentoConId(courseId);

                        // Aggiunta dell'insegnamento e del voto allo studente
                        insegnamentiStudente.put(insegnamento1, voto);

                        // Iscrizione dello studente all'insegnamento
                        insegnamento1.iscriviStudenteInInsegnamento(studenteNuovo);
                    }
                }

                // Impostazione degli insegnamenti iscritti dello studente
                studenteNuovo.setInsegnamentiIscritti(insegnamentiStudente);

                // Aggiunta dello studente alla lista degli studenti
                studenti.add(studenteNuovo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }
    /**
     * Restituisce la lista degli studenti.
     *
     * @return La lista degli studenti.
     */
    public static ArrayList<Studente> getStudenti() {
        return studenti;
    }

    /**
     * Restituisce l'insieme degli ID degli studenti.
     *
     * @return L'insieme degli ID degli studenti.
     */
    public static HashSet<String> getIdStudente() {
        return listaIdUtenti;
    }

    /**
     * Effettua il login di uno studente verificando le credenziali fornite (ID studente e password).
     * Se le credenziali sono corrette, restituisce l'oggetto Studente corrispondente; altrimenti, restituisce null.
     *
     * @param idStudente L'ID dello studente.
     * @param password   La password dello studente.
     * @return L'oggetto Studente se le credenziali sono corrette, altrimenti null.
     */
    public static Studente login(String idStudente, String password) {
        for (Studente studente : studenti) {
            if (studente.getIdStudente().equalsIgnoreCase(idStudente) && studente.getPassword().equals(password))
                return studente;
        }
        return null;
    }

    /**
     * Permette di aggiungere uno nuovo studente all'elenco degli studenti.
     * Richiede l'inserimento delle informazioni dello studente come ID, password, trimestre, nome, cognome, corso, numero di telefono e età.
     * Verifica che l'ID studente non sia già utilizzato prima di aggiungere lo studente.
     */
    public static void aggiungiStudente() {
        String nome, cognome, corsoStudente, numeroTelefono, idStudente, password;
        int età, trimestre;
        Scanner scanner = new Scanner(System.in);

        // Richiesta dell'ID studente e verifica se è già in uso
        System.out.printf("Per aggiungere uno/una studente/ssa nuovo, inserisci l'ID studente o digiti -1 per cancellare l'operazione: ");
        idStudente = scanner.nextLine();
        while (!idStudente.equals("-1") && listaIdUtenti.contains(idStudente.toLowerCase())) {
            System.out.print("Spiacente, quest'ID studente è già stato utilizzato! Riprova o digiti -1 per annullare l'operazione: ");
            idStudente = scanner.nextLine();
        }

        if (idStudente.equals("-1")) {
            System.out.printf("Operazione annullata!%n");
        } else {
            // Inserimento della password
            System.out.print("Inserisci la password: ");
            password = scanner.nextLine();

            // Inserimento del trimestre
            System.out.print("Inserisci il trimestre: ");
            trimestre = Main.getNumeriInteriValidi();
            while (trimestre <= 0 || trimestre > 4) {
                System.out.print("Il valore del trimestre deve essere nell'intervallo [1 , 4]. Per favore, riprova: ");
                trimestre = Main.getNumeriInteriValidi();
            }
            scanner = new Scanner(System.in);

            // Inserimento del nome
            System.out.print("Inserisci il nome: ");
            nome = scanner.nextLine();

            // Inserimento del cognome
            System.out.print("Inserisci il cognome: ");
            cognome = scanner.nextLine();

            // Inserimento del corso
            System.out.print("Inserisci il corso:\n" +
                    "1 - Cyber Security & Cloud Specialist\n" +
                    "2 - Digital Commerce and Performance Manager\n" +
                    "3 - Full Stack Developer & Integrator\n" +
                    "4 - Sviluppatore VR per Metaverso e Gaming\n" +
                    "5 - Cloud Developer\n" +
                    "6 - Data Management Specialist\n" +
                    "Inserisci il nome completo del corso desiderato: ");
            corsoStudente = scanner.nextLine();

            // Inserimento del numero di telefono
            System.out.print("Inserisci il numero di telefono: ");
            numeroTelefono = scanner.nextLine();

            // Inserimento dell'età
            System.out.print("Inserisci l'età: ");
            età = Main.getNumeriInteriValidi();
            while (età <= 0) {
                System.out.print("L'età deve essere > 0. Inserisci un'età valida: ");
                età = Main.getNumeriInteriValidi();
            }

            // Creazione dell'oggetto Studente e aggiunta alla lista degli studenti
            Studente studenteNuovo = new Studente(idStudente, password, trimestre, nome, cognome, corsoStudente, numeroTelefono, età);
            listaIdUtenti.add(idStudente.toLowerCase());
            studenti.add(studenteNuovo);
            System.out.printf("L'inserimento è stato effettuato con successo/a!%n");
        }
    }
    /**
     * Rimuove uno studente dall'elenco degli studenti.
     * Questa operazione comporta anche il ritiro dello studente da tutti gli insegnamenti a cui è iscritto.
     * @param studente Lo studente da rimuovere.
     */
    public static void eliminiStudente(Studente studente) {
        if (studente != null) {
            studente.ritiratiDaTuttiInsegnamenti(); // Ritira lo studente da tutti gli insegnamenti
            listaIdUtenti.remove(studente.getIdStudente()); // Rimuove l'ID studente dalla lista degli ID
            studenti.remove(studente); // Rimuove lo studente dall'elenco degli studenti
            System.out.printf("Studente rimosso con successo!%n");
        }
    }

    /**
     * Permette all'utente di selezionare uno studente dall'elenco fornito e restituisce lo studente selezionato.
     * @param studenti L'elenco di studenti tra cui scegliere.
     * @return Lo studente selezionato dall'utente, o null se l'utente annulla l'operazione.
     */
    public static Studente selezioniStudente(ArrayList<Studente> studenti){
        stampiDatiStudente(studenti); // Stampa i dati degli studenti disponibili
        Studente studente = null;
        if (studenti!= null && studenti.size() > 0) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Scegli lo studente che desideri o -1 per annullare l'operazione:");
            int indice = Main.getNumeriInteriValidi(); // Ottiene l'input dell'utente per selezionare uno studente
            while (indice != -1 && (indice <= 0 || indice > studenti.size())) {
                System.out.print("Per favore, inserisci un indice valido o digiti -1 per annullare l'operazione: ");
                indice = Main.getNumeriInteriValidi();
            }
            if (indice == -1)
                System.out.printf("Operazione annullata!%n");
            else {
                studente = studenti.get(indice - 1); // Restituisce lo studente selezionato (l'indice è ridotto di 1 rispetto all'input dell'utente)
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
        return studente;
    }
    /**
     * Stampa i dati degli studenti presenti nell'elenco fornito.
     * @param studenti L'elenco di studenti da stampare.
     */
    public static void stampiDatiStudente(ArrayList<Studente> studenti) {
        if (studenti != null && studenti.size() > 0) {
            studenti.sort(Comparator.comparingInt(Studente::getTrimestre)); // Ordina gli studenti per trimestre
            System.out.printf("%-10s%-15s%-25s%-15s%-60s%-20s%-10s%-37s%-5s%n", "Indice", "ID Studente", "Nome",
                    "Trimestre", "Corso","Numero di telefono", "Età", "Numero di insegnamenti iscritti", "Media");
            int i = 1;
            for (Studente studente : studenti) {
                // Stampa i dettagli di ciascuno studente formattati in una tabella
                System.out.printf("%-10s%-15s%-25s%-15s%-60s%-20s%-10s%-37s%-5s%n", i++, studente.getIdStudente(), studente.getNome() + " "
                                + studente.getCognome(), studente.getTrimestre(), studente.getCorso(),
                        studente.getNumeroTelefono(), studente.getEtà(), studente.getInsegnamentiIscriti().size(), studente.calcoliMedia());
            }
        }
        else
            System.out.printf("Non ci sono studenti!%n");
    }

    /**
     * Memorizza i dati degli studenti in un file di testo.
     */
    public static void memorizziDati() {
        try {
            FileWriter fileWriter = new FileWriter("./src/Studenti.txt"); // Apre un file in modalità scrittura
            for (Studente studente : studenti) {
                StringBuilder datiStudente = new StringBuilder(String.format(studente.getIdStudente() + "," + studente.getPassword() + ","
                        + studente.getTrimestre()
                        + "," + studente.getNome() + "," + studente.getCognome() + "," + studente.getCorso() + ","
                        + studente.getNumeroTelefono() + "," + studente.getEtà() + "\n"));
                ArrayList<Insegnamento> insegnamentiStudente = studente.getInsegnamentiIscriti();
                for (Insegnamento insegnamento : insegnamentiStudente)
                    datiStudente.append(String.format(insegnamento.getId() + ":" + studente.getVoto(insegnamento)+ ","));
                datiStudente.append("\n");
                fileWriter.write(String.valueOf(datiStudente)); // Scrive i dati dello studente nel file
            }
            fileWriter.close(); // Chiude il file
        } catch (IOException e) {
            System.out.print(e.getMessage()); // Gestione delle eccezioni in caso di errore
        }
    }
}
