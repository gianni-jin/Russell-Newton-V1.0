import java.util.List;

/**
 * Questa classe rappresenta una scuola e gestisce le informazioni relative ai professori, studenti e al bilancio.
 */

public class Scuola {
    private List<Professore> professori;  // Lista dei professori presenti nella scuola.
    private List<Studente> studenti;      // Lista degli studenti iscritti alla scuola.
    private static int incassiTotali;     // L'ammontare totale degli incassi della scuola.
    private static int costiTotali;       // L'ammontare totale dei costi della scuola.
    private static int finanziamento;      // Il finanziamento fornito alla scuola.
    private static int bilancio;           // Il bilancio totale della scuola.

    /**
     * Costruttore della classe Scuola.
     *
     * @param professori Lista dei professori presenti nella scuola.
     * @param studenti   Lista degli studenti iscritti alla scuola.
     */
    public Scuola(List<Professore> professori, List<Studente> studenti) {
        this.professori = professori;
        this.studenti = studenti;
        incassiTotali = 0;
        costiTotali = 0;
        finanziamento = 100000;
        bilancio = 0;
    }

    /**
     * Restituisce la lista dei professori presenti nella scuola.
     *
     * @return La lista dei professori.
     */
    public List<Professore> getProfessore() {
        return this.professori;
    }

    /**
     * Aggiunge un professore alla lista dei professori della scuola.
     *
     * @param professore Il professore da aggiungere.
     */
    public void aggiungiProfessore(Professore professore) {
        this.professori.add(professore);
    }

    /**
     * Restituisce la lista degli studenti iscritti alla scuola.
     *
     * @return La lista degli studenti.
     */
    public List<Studente> getStudente() {
        return this.studenti;
    }

    /**
     * Aggiunge uno studente alla lista degli studenti iscritti alla scuola.
     *
     * @param student Lo studente da aggiungere.
     */
    public void aggiungiStudente(Studente student) {
        this.studenti.add(student);
    }

    /**
     * Restituisce l'ammontare totale degli incassi della scuola.
     *
     * @return L'ammontare totale degli incassi.
     */
    public int getIncassiTotali() {
        return incassiTotali;
    }

    /**
     * Restituisce l'ammontare del finanziamento fornito alla scuola.
     *
     * @return L'ammontare del finanziamento.
     */
    public int getFinanziamento() {
        return finanziamento;
    }

    /**
     * Restituisce l'ammontare totale dei costi della scuola.
     *
     * @return L'ammontare totale dei costi.
     */
    public int getCostiTotali() {
        return costiTotali;
    }

    /**
     * Restituisce il bilancio totale della scuola.
     *
     * @return Il bilancio totale della scuola.
     */
    public int getBilancio() {
        return bilancio;
    }

    /**
     * Aggiorna l'ammontare totale degli incassi della scuola.
     *
     * @param incasso L'importo dell'incasso da aggiungere agli incassi totali.
     */
    public static void aggiorniIncassiTotali(int incasso) {
        incassiTotali += incasso;
        aggiorniBilancio(incassiTotali);
    }

    /**
     * Aggiorna l'ammontare totale dei costi della scuola.
     *
     * @param costi L'importo dei costi da aggiungere ai costi totali.
     */
    public static void aggiorniCostiTotali(int costi) {
        costiTotali += costi;
        aggiorniBilancio(costiTotali);
    }

    /**
     * Aggiorna il bilancio totale della scuola in base agli incassi e ai costi totali.
     *
     * @param risultato L'importo del risultato da aggiungere al bilancio.
     */
    public static void aggiorniBilancio(int risultato) {
        bilancio = finanziamento + incassiTotali - costiTotali;
    }
}
