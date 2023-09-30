/**
 * Questa classe rappresenta uno studente all'interno di un istituto scolastico.
 * Ogni studente è identificato da un ID univoco e ha informazioni personali come nome,
 * classe di appartenenza e stato del pagamento delle tasse scolastiche.
 */
public class Studente {
    private int idStudente;          // L'ID univoco dello studente.
    private String nomeStudente;     // Il nome dello studente.
    private String classe;           // La classe di appartenenza dello studente.
    private int tasseScolastichePagate = 0;  // L'importo delle tasse scolastiche pagate dallo studente.
    private int tasseScolasticheDovute = 106; // L'importo totale delle tasse scolastiche dovute.

    /**
     * Costruttore della classe Studente.
     *
     * @param idStudente   L'ID univoco dello studente.
     * @param nomeStudente Il nome dello studente.
     * @param classe       La classe di appartenenza dello studente.
     */
    public Studente(int idStudente, String nomeStudente, String classe) {
        this.idStudente = idStudente;
        this.nomeStudente = nomeStudente;
        this.classe = classe;
    }

    /**
     * Imposta la classe di appartenenza dello studente.
     *
     * @param classe La nuova classe di appartenenza dello studente.
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Registra il pagamento delle tasse scolastiche da parte dello studente.
     *
     * @param tasse L'importo delle tasse da pagare.
     */
    public void paghiTasse(int tasse) {
        this.tasseScolastichePagate += tasse;
        // Aggiorna l'incasso totale della scuola con l'importo delle tasse pagate.
        Scuola.aggiorniIncassiTotali(this.tasseScolastichePagate);
    }

    /**
     * Restituisce l'ID dello studente.
     *
     * @return L'ID dello studente.
     */
    public int getIdStudente() {
        return this.idStudente;
    }

    /**
     * Restituisce il nome dello studente.
     *
     * @return Il nome dello studente.
     */
    public String getNomeStudente() {
        return this.nomeStudente;
    }

    /**
     * Restituisce la classe di appartenenza dello studente.
     *
     * @return La classe di appartenenza dello studente.
     */
    public String getClasse() {
        return this.classe;
    }

    /**
     * Restituisce l'importo delle tasse scolastiche pagate dallo studente.
     *
     * @return L'importo delle tasse scolastiche pagate.
     */
    public int getTasseScolastichePagate() {
        return this.tasseScolastichePagate;
    }

    /**
     * Restituisce l'importo totale delle tasse scolastiche dovute.
     *
     * @return L'importo totale delle tasse scolastiche dovute.
     */
    public int getTasseScolasticheDovute() {
        return this.tasseScolasticheDovute;
    }

    /**
     * Calcola l'importo rimanente delle tasse scolastiche da pagare.
     *
     * @return L'importo rimanente delle tasse scolastiche da pagare.
     */
    public int getTasseDovuteRimanenti() {
        return this.tasseScolasticheDovute - this.tasseScolastichePagate;
    }

    /**
     * Restituisce una rappresentazione testuale dello studente, includendo il nome e l'importo delle tasse pagate.
     *
     * @return Una stringa con il nome dello studente e l'importo delle tasse pagate.
     */
    public String toString() {
        return this.nomeStudente + " ha pagato " + this.tasseScolastichePagate + " € di tasse scolastiche.";
    }
}
