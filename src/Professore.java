/**
 * Questa classe rappresenta un professore o una professoressa e gestisce le informazioni relative al loro ruolo e stipendio.
 */
public class Professore {
    private int idProf;         // L'ID univoco del professore.
    private String nomeProf;    // Il nome del professore.
    public int ral;       // Lo stipendio dei professori.
    public int ralTotale; // L'ammontare totale guadagnato da tutti i professori.

    /**
     * Costruttore della classe Professore.
     *
     * @param idProf   L'ID univoco del professore.
     * @param nomeProf Il nome del professore.
     * @param ral      Lo stipendio fisso per il professore specifico.
     */
    public Professore(int idProf, String nomeProf, int ral) {
        this.idProf = idProf;
        this.nomeProf = nomeProf;
        this.ral = ral;
        ralTotale = 0;
    }

    /**
     * Restituisce l'ID del professore.
     *
     * @return L'ID del professore.
     */
    public int getIdProf() {
        return this.idProf;
    }

    /**
     * Restituisce il nome del professore.
     *
     * @return Il nome del professore.
     */
    public String getNomeProf() {
        return this.nomeProf;
    }

    /**
     * Restituisce lo stipendio fisso per tutti i professori.
     *
     * @return Lo stipendio fisso.
     */
    public int getRal() {
        return ral;
    }

    /**
     * Imposta un nuovo stipendio fisso per tutti i professori.
     *
     * @param ral Il nuovo stipendio fisso da impostare.
     */
    public void setRal(int ral) {
        this.ral = ral;
    }

    /**
     * Registra il pagamento dello stipendio al professore e aggiorna l'ammontare totale dei costi della scuola.
     *
     * @param ral Lo stipendio da pagare al professore.
     */
    public void paghiSalario(int ral) {
        ralTotale += ral;
        Scuola.aggiorniCostiTotali(ral);
    }

    /**
     * Restituisce una stringa rappresentante il nome del professore e l'ammontare totale guadagnato.
     *
     * @return Una stringa rappresentante il nome del professore e l'ammontare totale guadagnato.
     */
    public String toString() {
        return "Nome del professore o della professoressa: " + this.nomeProf + "ha stipendio guadagnato  €" + ralTotale;
    }
}
