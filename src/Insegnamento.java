import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Insegnamento {
    // Attributi della classe Insegnamento
    private String id; // Identificatore univoco dell'insegnamento
    private String nome; // Nome dell'insegnamento
    private String descrizione; // Descrizione dell'insegnamento
    private String corsoInsegnamento; // Corso a cui è associato l'insegnamento
    private double votoMassimo; // Il massimo punteggio ottenibile in questo insegnamento
    private double votoMinimo; // Il punteggio minimo accettabile per superare l'insegnamento
    private double sufficienza; // Il punteggio minimo per ottenere la sufficienza in questo insegnamento
    private int trimestre; // Il trimestre in cui si tiene l'insegnamento
    private final ArrayList<Studente> studentiIscritti = new ArrayList<>(); // Elenco degli studenti iscritti a questo insegnamento

    /**
     * Costruttore della classe Insegnamento.
     *
     * @param id                L'identificatore univoco dell'insegnamento.
     * @param trimestre         Il trimestre in cui si tiene l'insegnamento (deve essere compreso tra 1 e 4).
     * @param nome              Il nome dell'insegnamento.
     * @param descrizione       La descrizione dell'insegnamento.
     * @param corsoInsegnamento Il corso al quale è associato l'insegnamento.
     * @param votoMassimo       Il massimo punteggio ottenibile in questo insegnamento (deve essere maggiore o uguale a votoMinimo).
     * @param votoMinimo        Il punteggio minimo accettabile per superare l'insegnamento (deve essere maggiore o uguale a 0).
     * @param sufficienza       Il punteggio minimo per ottenere la sufficienza in questo insegnamento (deve essere compreso tra votoMinimo e votoMassimo).
     * @throws IllegalArgumentException Se uno dei parametri è fuori dall'intervallo ammissibile.
     */
    public Insegnamento(String id, int trimestre, String nome, String descrizione,
                        String corsoInsegnamento, double votoMassimo, double votoMinimo, double sufficienza) {
        if (votoMassimo < 0 || votoMassimo < votoMinimo)
            throw new IllegalArgumentException("Il voto massimo deve essere nell'intervallo [voto minimo indicato , infinito]");
        if (votoMinimo < 0)
            throw new IllegalArgumentException("Il voto minimo deve essere nell'intervallo [0, voto massimo indicato]");
        if (sufficienza > votoMassimo || sufficienza < votoMinimo)
            throw new IllegalArgumentException("Il voto per la sufficienza deve essere nell'intervallo [voto minimo indicato, voto massimo indicato]");
        if (trimestre <= 0 || trimestre > 4)
            throw new IllegalArgumentException("Il valore trimestre deve essere nell'intervallo [1,4]");

        // Inizializzazione degli attributi della classe Insegnamento
        this.id = id;
        this.nome = nome;
        this.trimestre = trimestre;
        this.descrizione = descrizione;
        this.votoMassimo = votoMassimo;
        this.votoMinimo = votoMinimo;
        this.sufficienza = sufficienza;
        this.corsoInsegnamento = corsoInsegnamento;
    }
    /**
     * Restituisce l'identificatore univoco dell'insegnamento.
     *
     * @return L'identificatore univoco dell'insegnamento.
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta l'identificatore univoco dell'insegnamento.
     *
     * @param id Il nuovo identificatore univoco da assegnare all'insegnamento.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Consente all'utente di aggiornare l'identificatore dell'insegnamento.
     * Se il nuovo identificatore è valido e non esiste già, sostituisce l'identificatore precedente con il nuovo valore.
     */
    public void aggiorniInsegnamentoId() {
        HashMap<String, Insegnamento> insegnamentiId = InsegnamentoController.getIdInsegnamento();
        System.out.print("Inserisci il nuovo ID o -1 per annullare l'operazione: ");
        Scanner scanner = new Scanner(System.in);
        String idNuovo = scanner.nextLine();
        while (insegnamentiId.containsKey(idNuovo) && !idNuovo.equals("-1")) {
            System.out.print("Spiacente, questo ID è già stato usato. Per favore, inserisci un altro ID o digita -1 per annullare l'operazione.: ");
            idNuovo = scanner.nextLine();
        }
        if (idNuovo.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            insegnamentiId.put(idNuovo, this);
            insegnamentiId.remove(getId());
            setId(idNuovo);
            System.out.printf("ID dell'insegnamento aggiornato a %s%n", idNuovo);
        }
    }
    /**
     * Restituisce il nome dell'insegnamento.
     *
     * @return Il nome dell'insegnamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'insegnamento.
     *
     * @param nome Il nuovo nome da assegnare all'insegnamento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Consente all'utente di aggiornare il nome dell'insegnamento.
     * Se il nuovo nome è valido e diverso da "-1", sostituisce il nome precedente con il nuovo valore.
     */
    public void aggiorniNome() {
        System.out.print("Inserisci il nuovo nome o -1 per annullare l'operazione: ");
        Scanner scanner = new Scanner(System.in);
        String nomeNuovo = scanner.nextLine();
        if (nomeNuovo.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setNome(nomeNuovo);
            System.out.printf("Nome dell'insegnamento aggiornato a %s%n", nomeNuovo);
        }
    }

    /**
     * Restituisce la descrizione dell'insegnamento.
     *
     * @return La descrizione dell'insegnamento.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione dell'insegnamento.
     *
     * @param descrizione La nuova descrizione da assegnare all'insegnamento.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Consente all'utente di aggiornare la descrizione dell'insegnamento.
     * Se la nuova descrizione è valida e diversa da "-1", sostituisce la descrizione precedente con il nuovo valore.
     */
    public void aggiorniDescrizione() {
        System.out.print("Inserisci la nuova descrizione o digiti -1 per annullare l'operazione: ");
        Scanner scanner = new Scanner(System.in);
        String nuovaDescrizione = scanner.nextLine();
        if (nuovaDescrizione.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setDescrizione(nuovaDescrizione);
            System.out.printf("Descrizione dell'insegnamento aggiornata a %s%n", nuovaDescrizione);
        }
    }

    /**
     * Restituisce il corso di studio associato all'insegnamento.
     *
     * @return Il corso di studio dell'insegnamento.
     */
    public String getCorsoInsegnamento() {
        return corsoInsegnamento;
    }

    /**
     * Imposta il corso di studio associato all'insegnamento.
     *
     * @param corsoInsegnamento Il nuovo corso di studio da assegnare all'insegnamento.
     */
    public void setCorsoInsegnamento(String corsoInsegnamento) {
        this.corsoInsegnamento = corsoInsegnamento;
    }

    /**
     * Consente all'utente di aggiornare il corso di studio dell'insegnamento.
     * Se il nuovo corso di studio è valido e diverso da "-1", sostituisce il corso di studio precedente con il nuovo valore.
     */
    public void aggiorniCorsoStudenteInsegnamento() {
        System.out.print("Inserisci il corso di studio o digiti -1 per annullare l'operazione:\n" +
                "1 - Cyber Security & Cloud Specialist\n" +
                "2 - Digital Commerce and Performance Manager\n" +
                "3 - Full Stack Developer & Integrator\n" +
                "4 - Sviluppatore VR per Metaverso e Gaming\n" +
                "5 - Cloud Developer\n" +
                "6 - Data Management Specialist\n" +
                "Inserisci il nome completo del corso desiderato: ");
        Scanner scanner = new Scanner(System.in);
        String nuovoCorsoInsegnamento = scanner.nextLine();
        if (nuovoCorsoInsegnamento.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setCorsoInsegnamento(nuovoCorsoInsegnamento);
            System.out.printf("Corso dell'insegnamento aggiornato a %s%n", nuovoCorsoInsegnamento);
        }
    }
    /**
     * Restituisce il voto massimo consentito per questo insegnamento.
     *
     * @return Il voto massimo per l'insegnamento.
     */
    public double getVotoMassimo() {
        return votoMassimo;
    }

    /**
     * Imposta il voto massimo consentito per questo insegnamento.
     *
     * @param votoMassimo Il nuovo voto massimo da assegnare all'insegnamento.
     */
    public void setVotoMassimo(double votoMassimo) {
        this.votoMassimo = votoMassimo;
    }

    /**
     * Consente all'utente di aggiornare il voto massimo dell'insegnamento.
     * Se il nuovo voto massimo è valido e diverso da "-1", sostituisce il voto massimo precedente con il nuovo valore.
     */
    public void aggiorniVotoMassimo() {
        System.out.print("Inserisci il nuovo voto massimo o digiti -1 per annullare l'operazione: ");
        double nuovoVotoMassimo = Main.getNumeriDecimaliValidi();
        while (nuovoVotoMassimo != -1 && (nuovoVotoMassimo < 0 || nuovoVotoMassimo < votoMinimo)) {
            System.out.printf("Il voto massimo deve essere nell'intervallo [voto minimo indicato, infinito]. Riprova o digita -1 per annullare l'operazione: ");
            nuovoVotoMassimo = Main.getNumeriDecimaliValidi();
        }
        if (nuovoVotoMassimo == -1)
            System.out.printf("Operazione annullata!%n");
        else {
            setVotoMassimo(nuovoVotoMassimo);
            System.out.printf("Voto massimo è aggiornato a %.2f%n", nuovoVotoMassimo);
        }
    }
    /**
     * Restituisce il voto minimo consentito per questo insegnamento.
     *
     * @return Il voto minimo per l'insegnamento.
     */
    public double getVotoMinimo() {
        return votoMinimo;
    }

    /**
     * Imposta il voto minimo consentito per questo insegnamento.
     *
     * @param votoMinimo Il nuovo voto minimo da assegnare all'insegnamento.
     */
    public void setVotoMinimo(double votoMinimo) {
        this.votoMinimo = votoMinimo;
    }

    /**
     * Consente all'utente di aggiornare il voto minimo dell'insegnamento.
     * Se il nuovo voto minimo è valido e diverso da "-1", sostituisce il voto minimo precedente con il nuovo valore.
     */
    public void aggiorniVotoMinimo() {
        System.out.print("Inserisci il nuovo voto minimo indicato o -1 per annullare l'operazione: ");
        double nuovoVotoMinimo = Main.getNumeriDecimaliValidi();
        while (nuovoVotoMinimo != -1 && (nuovoVotoMinimo < 0 || nuovoVotoMinimo > votoMassimo)) {
            System.out.printf("Il voto minimo deve essere nell'intervallo [0 , voto massimo indicato]. Riprova o digiti -1 per annullare l'operazione: ");
            nuovoVotoMinimo = Main.getNumeriDecimaliValidi();
        }
        if (nuovoVotoMinimo == -1)
            System.out.printf("Operazione annullata!%n");
        else {
            setVotoMinimo(nuovoVotoMinimo);
            System.out.printf("Voto minimo aggiornato a %.2f%n", nuovoVotoMinimo);
        }
    }

    /**
     * Restituisce il voto minimo richiesto per la sufficienza in questo insegnamento.
     *
     * @return Il voto minimo richiesto per la sufficienza.
     */
    public double getSufficienza() {
        return sufficienza;
    }

    /**
     * Imposta il voto minimo richiesto per la sufficienza in questo insegnamento.
     *
     * @param sufficienza Il nuovo voto minimo richiesto per la sufficienza.
     */
    public void setSufficienza(double sufficienza) {
        this.sufficienza = sufficienza;
    }

    /**
     * Consente all'utente di aggiornare il voto richiesto per la sufficienza dell'insegnamento.
     * Se il nuovo voto per la sufficienza è valido e diverso da "-1", sostituisce il voto per la sufficienza precedente con il nuovo valore.
     */
    public void aggiorniSufficienza() {
        System.out.print("Inserisci il nuovo voto richiesto per la sufficienza o -1 per annullare l'operazione: ");
        double nuovaSufficienza = Main.getNumeriDecimaliValidi();
        while (nuovaSufficienza != -1 && (nuovaSufficienza < votoMinimo || nuovaSufficienza > votoMassimo)) {
            System.out.printf("Il voto per la sufficienza deve essere nell'intervallo [voto minimo indicato , voto massimo indicato]. Riprova o digiti -1 per annullare l'operazione: ");
            nuovaSufficienza = Main.getNumeriDecimaliValidi();
        }
        if (nuovaSufficienza == -1)
            System.out.printf("Operazione annullata!%n");
        else {
            setSufficienza(nuovaSufficienza);
            System.out.printf("Il voto per la sufficienza aggiornato a %.2f%n", nuovaSufficienza);
        }
    }
    /**
     * Restituisce il trimestre a cui è associato questo insegnamento.
     *
     * @return Il trimestre dell'insegnamento.
     */
    public int getTrimestre() {
        return trimestre;
    }

    /**
     * Imposta il trimestre a cui è associato questo insegnamento.
     *
     * @param trimestre Il nuovo trimestre da assegnare all'insegnamento.
     */
    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * Consente all'utente di aggiornare il trimestre dell'insegnamento.
     * Se il nuovo trimestre è valido e diverso da "-1", sostituisce il trimestre precedente con il nuovo valore.
     * Attenzione: tutti gli studenti iscritti a questo insegnamento saranno ritirati se si cambia il trimestre.
     */
    public void aggiorniTrimestre() {
        System.out.printf("Attenzione, tutti gli studenti iscritti a questo insegnamento saranno ritirati%nInserisci il nuovo trimestre o -1 per annullare l'operazione: ");
        int nuovoTrimestre = Main.getNumeriInteriValidi();
        while (nuovoTrimestre != -1 && (nuovoTrimestre <= 0 || nuovoTrimestre > 4)) {
            System.out.print("Il trimestre dell'insegnamento deve essere nell'intervallo [1 , 4]. Riprova o digita -1 per annullare l'operazione: ");
            nuovoTrimestre = Main.getNumeriInteriValidi();
        }
        if (nuovoTrimestre == -1) {
            System.out.printf("Operazione annullata%n");
        } else {
            rimuoviTuttiStudentiDaInsegnamento();
            setTrimestre(nuovoTrimestre);
            System.out.printf("Trimestre dell'insegnamento aggiornato a %d%n", nuovoTrimestre);
        }
    }
    /**
     * Restituisce il numero di studenti attualmente iscritti a questo insegnamento.
     *
     * @return Il numero di studenti iscritti a questo insegnamento.
     */
    public int getiNumeroStudent(){
        return studentiIscritti.size();
    }


    /**
     * Consente all'utente di selezionare e eseguire operazioni di aggiornamento sull'insegnamento corrente.
     * L'utente può scegliere tra varie opzioni, inclusa la modifica dell'ID, del nome, della descrizione,
     * del corso, del voto massimo, del voto minimo, del voto di successo e del trimestre dell'insegnamento.
     * L'opzione "Torni indietro" consente di uscire dalla modalità di modifica.
     */
    public void modifichiInsegnamento() {
        System.out.printf("Operazioni di aggiornamento per l'insegnamento: %n" +
                "1- Aggiorni l'ID dell'insegnamento%n" +
                "2- Aggiorni il nome dell'insegnamento%n" +
                "3- Aggiorni la descrizione dell'insegnamento%n" +
                "4- Aggiorni il corso dell'insegnamento%n" +
                "5- Aggiorni il voto massimo dell'insegnamento%n" +
                "6- Aggiorni il voto minimo dell'insegnamento%n" +
                "7- Aggiorni il voto di successo dell'insegnamento%n" +
                "8- Aggiorni il trimestre dell'insegnamento%n" +
                "9- Torni indietro%n" +
                "\"Scegli un'opzione, per favore. Cosa desideri aggiornare? ");

        int aggiornamentoOpzione = Main.opzioneValida(9);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

        if (aggiornamentoOpzione == 1)
            aggiorniInsegnamentoId();

        else if (aggiornamentoOpzione == 2)
            aggiorniNome();

        else if (aggiornamentoOpzione == 3)
            aggiorniDescrizione();

        else if (aggiornamentoOpzione == 4)
            aggiorniCorsoStudenteInsegnamento();

        else if (aggiornamentoOpzione == 5)
            aggiorniVotoMassimo();

        else if (aggiornamentoOpzione == 6)
            aggiorniVotoMinimo();

        else if (aggiornamentoOpzione == 7)
            aggiorniSufficienza();

        else if (aggiornamentoOpzione == 8)
            aggiorniTrimestre();

        else if (aggiornamentoOpzione == 9)
            return;

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        modifichiInsegnamento();
    }

    /**
     * Iscrive uno studente a questo insegnamento aggiungendolo alla lista degli studenti iscritti.
     *
     * @param studente Lo studente da iscrivere all'insegnamento.
     */
    public void iscriviStudenteInInsegnamento(Studente studente){
        studentiIscritti.add(studente);
    }

    /**
     * Rimuove uno studente da questo insegnamento rimuovendolo dalla lista degli studenti iscritti.
     *
     * @param studente Lo studente da rimuovere dall'insegnamento.
     */
    public void rimuoviStudenteDaInsegnamento(Studente studente){
        studentiIscritti.remove(studente);
    }

    /**
     * Rimuove tutti gli studenti da questo insegnamento. Questo metodo verrà utilizzato quando si modifica il trimestre dell'insegnamento.
     */
    public void rimuoviTuttiStudentiDaInsegnamento(){
        while (studentiIscritti.size() > 0)
            studentiIscritti.get(0).ritiratiDaInsegnamento(this, false);
    }

    /**
     * Visualizza la lista degli studenti iscritti a questo insegnamento.
     * Stampa i dati degli studenti iscritti, se ce ne sono, altrimenti mostra un messaggio che indica che non ci sono studenti iscritti.
     */
    public void visualizziStudentiIscritti() {
        if (studentiIscritti.size() > 0) {
            System.out.printf("Studenti iscritti all'insegnamento %s : %n", getNome());
            StudenteController.stampiDatiStudente(studentiIscritti);
        }
        else
            System.out.printf("Non ci sono studenti iscritti a questo insegnamento!%n");
    }
}