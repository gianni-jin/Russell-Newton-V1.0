/**
 * Questa classe rappresenta un professore o una professoressa e gestisce le informazioni relative al loro ruolo e stipendio.
 */
public class Professore {
    private int idProf;         // L'ID univoco del professore.
    private String nomeProf;    // Il nome del professore.
    public static int ral;       // Lo stipendio fisso per tutti i professori.
    public static int ralGuadagnata; // L'ammontare totale guadagnato da tutti i professori.

    /**
     * Costruttore della classe Professore.
     *
     * @param idProf   L'ID univoco del professore.
     * @param nomeProf Il nome del professore.
     * @param ral      Lo stipendio fisso per tutti i professori.
     */
    public Professore(int idProf, String nomeProf, int ral) {
        this.idProf = idProf;
        this.nomeProf = nomeProf;
        Professore.ral = ral;
        ralGuadagnata = 0;
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
    public void setSalary(int ral) {
        Professore.ral = ral;
    }

    /**
     * Registra il pagamento dello stipendio al professore e aggiorna l'ammontare totale dei costi della scuola.
     *
     * @param ral Lo stipendio da pagare al professore.
     */
    public void paghiSalario(int ral) {
        ralGuadagnata += ral;
        Scuola.aggiorniCostiTotali(ral);
    }

    /**
     * Restituisce una stringa rappresentante il nome del professore e l'ammontare totale guadagnato.
     *
     * @return Una stringa rappresentante il nome del professore e l'ammontare totale guadagnato.
     */
    public String toString() {
        return "Nome del professore o della professoressa: " + this.nomeProf + " Totale stipendio guadagnato finora €" + ralGuadagnata;
    }
}
