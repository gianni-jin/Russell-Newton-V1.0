import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * La classe InsegnamentoController gestisce gli insegnamenti e fornisce metodi per gestire e manipolare
 * le informazioni sugli insegnamenti.
 */
public final class InsegnamentoController {

    // Lista degli insegnamenti
    private static final ArrayList<Insegnamento> insegnamenti = new ArrayList<>();

    // Mappa degli insegnamenti basata su ID
    private static final HashMap<String, Insegnamento> insegnamentiId = new HashMap<>();

    private InsegnamentoController(){
    }
    /**
     * Legge i dati degli insegnamenti da un file e li carica nella lista degli insegnamenti e nella mappa degli insegnamenti ID.
     * Il file deve essere formattato con le informazioni sugli insegnamenti separate da virgole. Le informazioni lette
     * dal file includono l'ID, il trimestre, il nome, la descrizione, il corso dell'insegnamento, il voto massimo,
     * il voto minimo e il voto di sufficienza.
     * I dati letti vengono utilizzati per creare nuovi oggetti Insegnamento, che vengono quindi aggiunti alla lista
     * degli insegnamenti e alla mappa degli insegnamenti ID.
     */
    public static void leggiDati() {
        File file = new File("./src/Insegnamenti.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] insegnamentoDati = scanner.nextLine().split(",");
                Insegnamento nuovoInsegnamento = new Insegnamento(insegnamentoDati[0], Integer.parseInt(insegnamentoDati[1]),
                        insegnamentoDati[2], insegnamentoDati[3], insegnamentoDati[4], Double.parseDouble(insegnamentoDati[5]),
                        Double.parseDouble(insegnamentoDati[6]),
                        Double.parseDouble(insegnamentoDati[7]));
                insegnamenti.add(nuovoInsegnamento);
                insegnamentiId.put(nuovoInsegnamento.getId(), nuovoInsegnamento);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File non trovato!%n");
        }
    }

    /**
     * Restituisce la lista di tutti gli insegnamenti disponibili.
     *
     * @return Una lista di oggetti Insegnamento.
     */
    public static ArrayList<Insegnamento> getInsegnamenti() {
        return insegnamenti;
    }

    /**
     * Restituisce la mappa degli insegnamenti associati ai loro ID.
     *
     * @return Una mappa contenente gli ID degli insegnamenti come chiavi e gli oggetti Insegnamento come valori.
     */
    public static HashMap<String, Insegnamento> getIdInsegnamento() {
        return insegnamentiId;
    }

    /**
     * Restituisce un insegnamento specifico dato il suo ID.
     *
     * @param id L'ID dell'insegnamento da cercare.
     * @return L'oggetto Insegnamento corrispondente all'ID specificato o null se non esiste.
     */
    public static Insegnamento getInsegnamentoConId(String id) {
        return insegnamentiId.get(id);
    }

    /**
     * Consente di aggiungere un nuovo insegnamento alla lista degli insegnamenti disponibili.
     */
    public static void aggiungiInsegnamento() {
        // Dichiarazione delle variabili per memorizzare i dati del nuovo insegnamento
        String id, nome, descrizione, corsoInsegnamento;
        double votoMassimo, votoMinimo, sufficienza;
        int trimestre;

        Scanner scanner = new Scanner(System.in);

        // Richiesta dell'ID per il nuovo insegnamento
        System.out.printf("Per aggiungere un nuovo insegnamento:%nInserisci l'ID o -1 per annullare l'operazione: ");
        id = scanner.nextLine();

        // Verifica se l'ID è già stato utilizzato
        while (insegnamentiId.containsKey(id) && !id.equals("-1")) {
            System.out.print("Spiacente, questo ID è stato già usato. Inserisci un altro ID o -1 per annullare l'operazione");
            id = scanner.nextLine();
        }

        if (id.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            // Richiesta del trimestre dell'insegnamento
            System.out.print("Inserisci il trimestre dell'insegnamento : ");
            trimestre = Main.getNumeriInteriValidi();

            // Verifica che il trimestre sia valido (compreso tra 1 e 4)
            while (trimestre <= 0 || trimestre > 4) {
                System.out.print("Il trimestre dell'insegnamento deve essere compreso nell'intervallo [1 , 4]. Per favore, riprova.");
                trimestre = Main.getNumeriInteriValidi();
            }

            scanner = new Scanner(System.in);

            // Richiesta del nome dell'insegnamento
            System.out.print("Inserisci il nome: ");
            nome = scanner.nextLine();

            // Richiesta della descrizione dell'insegnamento
            System.out.print("Inserisci la descrizione: ");
            descrizione = scanner.nextLine();

            // Richiesta del corso di appartenenza dell'insegnamento
            System.out.print("Inserisci il corso a cui fa parte quest'insegnamento:\n" +
                    "1 - Cyber Security & Cloud Specialist\n" +
                    "2 - Digital Commerce and Performance Manager\n" +
                    "3 - Full Stack Developer & Integrator\n" +
                    "4 - Sviluppatore VR per Metaverso e Gaming\n" +
                    "5 - Cloud Developer\n" +
                    "6 - Data Management Specialist\n" +
                    "Inserisci il nome completo del corso desiderato: ");
            corsoInsegnamento = scanner.nextLine();
            scanner.nextLine();

            // Richiesta del voto massimo per l'insegnamento
            System.out.print("Inserisci il voto massimo: ");
            votoMassimo = Main.getNumeriDecimaliValidi();

            // Verifica che il voto massimo sia valido (maggiore di 0)
            while (votoMassimo <= 0){
                System.out.print("Il voto massimo deve essere > 0. Per favore, riprova: ");
                votoMassimo = Main.getNumeriDecimaliValidi();
            }

            // Richiesta del voto minimo per l'insegnamento
            System.out.print("Inserisci il voto minimo indicato: ");
            votoMinimo = Main.getNumeriDecimaliValidi();

            // Verifica che il voto minimo sia valido (minore del voto massimo)
            while (votoMinimo >= votoMassimo){
                System.out.print("Il voto minimo deve essere < voto massimo indicato. Per favore, riprova: ");
                votoMinimo = Main.getNumeriDecimaliValidi();
            }

            // Richiesta del voto di sufficienza per l'insegnamento
            System.out.print("Inserisci il voto per la sufficienza:");
            sufficienza = Main.getNumeriDecimaliValidi();

            // Verifica che il voto di sufficienza sia valido (compreso tra il voto minimo e il voto massimo)
            while (sufficienza > votoMassimo || sufficienza < votoMinimo){
                System.out.print("Il voto per la sufficienza deve essere nell'intervallo [voto minimo indicato, voto massimo indicato] please try again : ");
                sufficienza = Main.getNumeriDecimaliValidi();
            }

            // Creazione di un nuovo insegnamento e aggiunta alla lista degli insegnamenti
            Insegnamento nuovoInsegnamento = new Insegnamento(id, trimestre, nome, descrizione, corsoInsegnamento, votoMassimo, votoMinimo, sufficienza);
            insegnamenti.add(nuovoInsegnamento);
            insegnamentiId.put(id, nuovoInsegnamento);

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("Insegnamento aggiunto!%n");
        }
    }



    /**
     * Rimuove un insegnamento dalla lista degli insegnamenti.
     *
     * @param insegnamento L'insegnamento da rimuovere.
     */
    public static void rimuoviInsegnamento(Insegnamento insegnamento) {
        if (insegnamento != null) {
            insegnamentiId.remove(insegnamento.getId()); // Rimuove l'insegnamento dal registro degli ID
            insegnamento.rimuoviTuttiStudentiDaInsegnamento(); // Rimuove tutti gli studenti iscritti a questo insegnamento
            insegnamenti.remove(insegnamento); // Rimuove l'insegnamento dalla lista principale
            System.out.printf("Insegnamento rimosso con successo!%n");
        }
    }

    /**
     * Permette all'utente di selezionare un insegnamento dalla lista degli insegnamenti disponibili.
     *
     * @param insegnamenti Lista degli insegnamenti disponibili.
     * @param studente      Lo studente per cui visualizzare gli insegnamenti, o null per visualizzare tutti gli insegnamenti.
     * @return L'insegnamento selezionato dall'utente o null se l'operazione è stata annullata.
     */
    public static Insegnamento selezioniInsegnamento(ArrayList<Insegnamento> insegnamenti, Studente studente) {
        // Visualizza gli insegnamenti dello studente, se specificato, altrimenti visualizza tutti gli insegnamenti
        if (studente != null)
            studente.stampiStudenteInsegnamenti(studente.getInsegnamentiIscriti());
        else
            getDettagliInsegnamenti(insegnamenti);

        Insegnamento insegnamento = null;
        if (insegnamenti != null && insegnamenti.size() > 0) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Scegli l'insegnamento che desideri o digita -1 per annullare l'operazione: ");
            int indice = Main.getNumeriInteriValidi();

            // Verifica che l'indice sia valido o che l'operazione non sia stata annullata
            while (indice != -1 && (indice <= 0 || indice > insegnamenti.size())) {
                System.out.print("Per favore, inserisci un indice valido o -1 per annullare l'operazione: ");
                indice = Main.getNumeriInteriValidi();
            }

            if (indice == -1) {
                System.out.printf("Operazione annullata con successo!%n");
            } else {
                // Recupera l'insegnamento selezionato dall'utente
                insegnamento = insegnamenti.get(indice - 1);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
        return insegnamento;
    }
    /**
     * Visualizza i dettagli degli insegnamenti nella lista specificata.
     *
     * @param insegnamenti Lista degli insegnamenti da visualizzare.
     */
    public static void getDettagliInsegnamenti(ArrayList<Insegnamento> insegnamenti) {
        // Verifica che la lista degli insegnamenti non sia vuota
        if (insegnamenti != null && insegnamenti.size() > 0) {
            // Ordina gli insegnamenti per trimestre
            insegnamenti.sort(Comparator.comparingInt(Insegnamento::getTrimestre));

            // Stampa l'intestazione della tabella
            System.out.printf("%-10s%-20s%-70s%-30s%-60s%-20s%-20s%-20s%-20s%n", "Indice", "Insegnamento ID", "Insegnamento",
                    "Insegnamento trimestre", "Corso",
                    "Voto minimo", "Voto massimo", "Sufficienza", "Numero di studenti");

            int i = 1;
            // Itera attraverso gli insegnamenti e stampa i dettagli di ciascun insegnamento
            for (Insegnamento insegnamento : insegnamenti)
                System.out.printf("%-10s%-20s%-70s%-30s%-60s%-20s%-20s%-20s%-20s%n", i++, insegnamento.getId(), insegnamento.getNome(), insegnamento.getTrimestre(),
                        insegnamento.getCorsoInsegnamento(),
                        insegnamento.getVotoMinimo(), insegnamento.getVotoMassimo(), insegnamento.getSufficienza(), insegnamento.getiNumeroStudent());
        } else {
            // Messaggio di avviso se la lista degli insegnamenti è vuota
            System.out.printf("Non ci sono insegnamenti da visualizzare!%n");
        }
    }
    /**
     * Memorizza i dati degli insegnamenti nel file di testo "Insegnamenti.txt".
     */
    public static void memorizziDati() {
        try {
            // Crea un FileWriter per scrivere nel file "Insegnamenti.txt"
            FileWriter fileWriter = new FileWriter("./src/Insegnamenti.txt");

            // Itera attraverso gli insegnamenti e formatta i dati in una stringa CSV per ciascun insegnamento
            for (Insegnamento insegnamento : insegnamenti) {
                String insegnamentoDati = String.format(
                        "%s,%d,%s,%s,%s,%.2f,%.2f,%.2f%n",
                        insegnamento.getId(), insegnamento.getTrimestre(), insegnamento.getNome(),
                        insegnamento.getDescrizione(), insegnamento.getCorsoInsegnamento(),
                        insegnamento.getVotoMassimo(), insegnamento.getVotoMinimo(),
                        insegnamento.getSufficienza()
                );
                // Scrivi i dati dell'insegnamento nel file
                fileWriter.write(insegnamentoDati);
            }

            // Chiudi il FileWriter
            fileWriter.close();
        } catch (IOException e) {
            // Gestione dell'eccezione in caso di errore durante la scrittura nel file
            System.out.println(e.getMessage());
        }
    }

}