import java.util.*;

public class Studente {

    /**
     * Dati personali dello studente
     * Di cui, corsoStudente indica il corso (Full Stack Developer & Integrator ad esempio) si è iscritto/a lo studente/ssa
     * Poi trimestre indica il numero del trimestre che lo/a studente/ssa sta attualmente percorrendo nel suo percorso di
     * studi. Nel nostro caso, sarebbe un valore da 1 a 4
     * (6 mesi per il primo anno, ed gli altri 6 per il secondo)
     */
    private String nome, cognome, numeroTelefono, corsoStudente;
    private String idStudente, password;
    private int trimestre, età;

    // HashMap per mantenere gli insegnamenti a cui lo studente è iscritto con i rispettivi voti
    private HashMap<Insegnamento , Double> insegnamentiIscritti;

    /**
     * Costruttore della classe Studente.
     *
     * @param idStudente     L'ID univoco dello studente.
     * @param password       La password dello studente.
     * @param trimestre      Il trimestre in cui lo studente è iscritto (deve essere compreso tra 1 e 4).
     * @param nome           Il nome dello studente.
     * @param cognome        Il cognome dello studente.
     * @param corsoStudente  Il corso di studio dello studente.
     * @param numeroTelefono Il numero di telefono dello studente.
     * @param età            L'età dello studente.
     * @throws IllegalArgumentException Se i parametri trimestre o età sono invalidi.
     */
    public Studente(String idStudente, String password, int trimestre, String nome, String cognome, String corsoStudente, String numeroTelefono, int età){
        if (trimestre <= 0 || trimestre > 4)
            throw new IllegalArgumentException("Il trimestre dell'insegnamento deve essere nell'intervallo [1,4]");
        if(età <= 0 )
            throw new IllegalArgumentException("Età deve essere > 0");
        this.idStudente = idStudente;
        this.password = password;
        this.trimestre = trimestre;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefono= numeroTelefono;
        this.corsoStudente = corsoStudente;
        this.età = età;
        insegnamentiIscritti = new HashMap<>();
    }

    /**
     * Restituisce l'ID dello studente.
     *
     * @return L'ID dello studente.
     */
    public String getIdStudente() {
        return idStudente;
    }

    /**
     * Imposta un nuovo ID per lo studente.
     *
     * @param idStudente Il nuovo ID da impostare.
     */
    public void setIdStudente(String idStudente) {
        this.idStudente = idStudente;
    }

    public void aggiorniIdStudente() {
        // Creazione di uno scanner per l'input da tastiera
        Scanner scanner = new Scanner(System.in);

        // Ottenimento della lista degli ID studente esistenti
        HashSet<String> listaIdUtenti = StudenteController.getIdStudente();

        // Richiesta all'utente di inserire il nuovo ID studente o -1 per annullare l'operazione
        System.out.print("Inserisci il nuovo ID studente o digiti -1 per annullare l'operazione: ");
        String idStudenteNuovo = scanner.nextLine();

        // Ciclo per verificare se il nuovo ID studente è univoco
        while (!idStudenteNuovo.equals("-1") && listaIdUtenti.contains(idStudenteNuovo.toLowerCase())) {
            System.out.print("Spiacente, quest'ID studente è già stato utilizzato! Riprova o digiti -1 per annullare l'operazione: ");
            idStudenteNuovo = scanner.nextLine();
        }

        // Controllo se l'operazione è stata annullata
        if (idStudenteNuovo.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            // Rimozione dell'ID studente corrente dalla lista degli ID esistenti
            listaIdUtenti.remove(getIdStudente().toLowerCase());

            // Impostazione del nuovo ID studente
            setIdStudente(idStudenteNuovo);

            // Aggiunta del nuovo ID studente alla lista degli ID esistenti
            listaIdUtenti.add(idStudenteNuovo.toLowerCase());
            System.out.printf("ID studente aggiornato a %s%n", idStudenteNuovo);
        }
    }

    /**
     * Restituisce la password dell'oggetto Studente.
     *
     * @return La password dell'oggetto Studente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta una nuova password per l'oggetto Studente.
     *
     * @param password La nuova password da impostare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Consente all'utente di aggiornare la password dell'oggetto Studente.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire una nuova password.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito una nuova password valida (diversa da "-1"),
     * il metodo imposta la nuova password per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getPassword() Per ottenere la password attuale dell'oggetto Studente.
     * @see Studente#setPassword(String) Per impostare manualmente una nuova password per l'oggetto Studente.
     */
    public void aggiorniPassword(){
        // Creazione di uno scanner per l'input da tastiera
        Scanner scanner = new Scanner(System.in);

        // Richiesta all'utente di inserire la nuova password o digiti -1 per annullare l'operazione
        System.out.print("Inserisci la nuova password o digiti -1 per annullare l'operazione:");
        String passwordNuova = scanner.nextLine();

        // Controllo se l'operazione è stata annullata
        if (passwordNuova.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            // Impostazione della nuova password
            setPassword(passwordNuova);
            System.out.printf("Password aggiornata%n");
        }
    }

    /**
     * Restituisce il trimestre attuale dello studente.
     *
     * @return Il trimestre attuale dello studente.
     */
    public int getTrimestre(){
        return trimestre;
    }

    /**
     * Imposta un nuovo valore per il trimestre dello studente.
     *
     * @param trimestre Il nuovo trimestre da impostare.
     */
    public void setTrimestre(int trimestre){
        this.trimestre = trimestre;
    }
    /**
     * Consente all'utente di aggiornare il trimestre dello studente.
     * Questa operazione comporterà il ritiro da tutti gli insegnamenti attuali.
     *
     * Il metodo richiede all'utente di inserire un nuovo valore per il trimestre.
     * Se il valore inserito è nell'intervallo [1, 4], il trimestre viene aggiornato e
     * tutti gli insegnamenti attualmente registrati vengono ritirati e cancellati.
     * Se l'utente digita "-1", l'operazione viene annullata senza apportare modifiche.
     * Questo metodo è utile per consentire agli studenti di aggiornare le informazioni
     * del loro profilo, come il nome, quando necessario.
     *
     * @see Studente#getNome() Per ottenere il nome attuale dello studente.
     * @see Studente#setNome(String) Per impostare un nuovo nome per lo studente.
     */
    public void aggiorniTrimestre(){
        System.out.printf("Attenzione, tutti gli insegnamenti iscritti saranno ritirati%nInserisci il nuovo trimestre o -1 per annullare l'operazione:");

        int nuovoTrimestre = Main.getNumeriInteriValidi();
        while (nuovoTrimestre != -1 && (nuovoTrimestre <= 0 || nuovoTrimestre > 4)) {
            System.out.print("Il valore del trimestre deve essere nell'intervallo [1, 4]. Per favore, riprova o inserisci -1 per annullare l'operazione: ");
            nuovoTrimestre = Main.getNumeriInteriValidi();
        }

        if (nuovoTrimestre == -1)
            System.out.printf("Operazione annullata!%n");
        else {
            ritiratiDaTuttiInsegnamenti();
            insegnamentiIscritti.clear();
            setTrimestre(nuovoTrimestre);
            System.out.printf("Trimestre dello/a studente/ssa aggiornato a %d%n", nuovoTrimestre);
        }
    }
    /**
     * Restituisce il nome dello studente.
     *
     * @return Il nome dello studente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta un nuovo valore per il nome dello studente.
     *
     * @param nome Il nuovo nome da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Consente all'utente di aggiornare il nome dello studente.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire un nuovo nome.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito un nuovo nome valido (diverso da "-1"),
     * il metodo imposta il nuovo nome per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getNome() Per ottenere il nome attuale dell'oggetto Studente.
     * @see Studente#setNome(String) Per impostare manualmente un nuovo nome per l'oggetto Studente.
     */
    public void aggiorniNome() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nuovo nome o digiti -1 per annullare l'operazione: ");
        String nomeNuovo = scanner.nextLine();
        if (nomeNuovo.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setNome(nomeNuovo);
            System.out.printf("Nome aggiornato a %s%n", nomeNuovo);
        }
    }
    /**
     * Restituisce il cognome dello studente.
     *
     * @return Il cognome dello studente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta un nuovo valore per il cognome dello studente.
     *
     * @param cognome Il nuovo cognome da impostare.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Consente all'utente di aggiornare il cognome dello studente.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire un nuovo cognome.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito un nuovo cognome valido (diverso da "-1"),
     * il metodo imposta il nuovo cognome per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getCognome() Per ottenere il cognome attuale dell'oggetto Studente.
     * @see Studente#setCognome(String) Per impostare manualmente un nuovo cognome per l'oggetto Studente.
     */
    public void aggiorniCognome() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nuovo cognome o -1 per annullare l'operazione: ");
        String nuovoCognome = scanner.nextLine();
        if (nuovoCognome.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setCognome(nuovoCognome);
            System.out.printf("Cognome aggiornato a %s%n", nuovoCognome);
        }
    }
    /**
     * Restituisce il corso a cui lo studente è iscritto.
     *
     * @return Il corso a cui lo studente è iscritto.
     */
    public String getCorso() {
        return corsoStudente;
    }

    /**
     * Imposta un nuovo corso per lo studente.
     *
     * @param corsoStudente Il nuovo corso da impostare.
     */
    public void setCorso(String corsoStudente) {
        this.corsoStudente = corsoStudente;
    }

    /**
     * Consente all'utente di aggiornare il corso a cui lo studente è iscritto.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire un nuovo corso.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito un nuovo corso valido (diverso da "-1"),
     * il metodo imposta il nuovo corso per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getCorso() Per ottenere il corso attuale dell'oggetto Studente.
     * @see Studente#setCorso(String) Per impostare manualmente un nuovo corso per l'oggetto Studente.
     */
    public void aggiorniCorsoStudente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nuovo corso o digiti -1 per annullare l'operazione: ");
        String nuovoCorso = scanner.nextLine();
        if (nuovoCorso.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setCorso(nuovoCorso);
            System.out.printf("Corso aggiornato a %s%n", nuovoCorso);
        }
    }
    /**
     * Restituisce il numero di telefono dello studente.
     *
     * @return Il numero di telefono dello studente.
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Imposta un nuovo numero di telefono per lo studente.
     *
     * @param numeroTelefono Il nuovo numero di telefono da impostare.
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Consente all'utente di aggiornare il numero di telefono dello studente.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire un nuovo numero di telefono.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito un nuovo numero di telefono valido (diverso da "-1"),
     * il metodo imposta il nuovo numero di telefono per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getNumeroTelefono() Per ottenere il numero di telefono attuale dell'oggetto Studente.
     * @see Studente#setNumeroTelefono(String) Per impostare manualmente un nuovo numero di telefono per l'oggetto Studente.
     */
    public void aggiorniNumeroTelefono() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nuovo numero di telefono o digiti -1 per annullare l'operazione: ");
        String nuovoNumeroTelefono = scanner.nextLine();
        if (nuovoNumeroTelefono.equals("-1"))
            System.out.printf("Operazione annullata!%n");
        else {
            setNumeroTelefono(nuovoNumeroTelefono);
            System.out.printf("Numero di telefono aggiornato a %s%n", nuovoNumeroTelefono);
        }
    }
    /**
     * Restituisce l'età dello studente.
     *
     * @return L'età dello studente.
     */
    public int getEtà() {
        return età;
    }

    /**
     * Imposta una nuova età per lo studente.
     *
     * @param età La nuova età da impostare.
     */
    public void setEtà(int età) {
        this.età = età;
    }

    /**
     * Consente all'utente di aggiornare l'età dello studente.
     * Questo metodo avvia un processo interattivo che richiede all'utente di inserire una nuova età.
     * L'utente può anche digitare "-1" per annullare l'operazione senza apportare modifiche.
     *
     * Una volta che l'utente ha inserito una nuova età valida (maggiore o uguale a -1),
     * il metodo imposta la nuova età per l'oggetto Studente e conferma l'avvenuto aggiornamento.
     *
     * @see Studente#getEtà() Per ottenere l'età attuale dell'oggetto Studente.
     * @see Studente#setEtà(int) Per impostare manualmente una nuova età per l'oggetto Studente.
     */
    public void aggiorniEtà() {
        System.out.print("Inserisci la nuova età o digiti -1 per annullare l'operazione: ");
        int etàNuova = Main.getNumeriInteriValidi();
        while (etàNuova < -1) {
            System.out.print("Inserisci la nuova età o digiti -1 per annullare l'operazione: ");
            etàNuova = Main.getNumeriInteriValidi();
        }
        if (etàNuova == -1)
            System.out.printf("Operazione annullata!%n");
        else {
            setEtà(etàNuova);
            System.out.printf("Età aggiornata a %d%n", etàNuova);
        }
    }

    /**
     * Consente all'utente di effettuare diverse operazioni di aggiornamento sui dati dello studente.
     * Questo metodo visualizza un menu con le diverse opzioni di aggiornamento disponibili
     * e richiede all'utente di selezionare un'opzione.
     * Dopo l'aggiornamento, l'utente ha la possibilità di effettuare ulteriori modifiche o tornare indietro.
     *
     * Le opzioni di aggiornamento includono l'ID studente, la password, il trimestre, il nome, il cognome,
     * il corso, il numero di telefono, l'età e gli insegnamenti dello studente.
     * È anche possibile tornare indietro nel menu principale selezionando l'opzione 10.
     *
     * Questo metodo utilizza altri metodi specifici per eseguire l'aggiornamento dei vari campi,
     * come aggiorniIdStudente(), aggiorniPassword(), etc.
     *
     * @see Studente#aggiorniIdStudente() Per l'aggiornamento dell'ID studente.
     * @see Studente#aggiorniPassword() Per l'aggiornamento della password.
     * @see Studente#aggiorniTrimestre() Per l'aggiornamento del trimestre.
     * @see Studente#aggiorniNome() Per l'aggiornamento del nome.
     * @see Studente#aggiorniCognome() Per l'aggiornamento del cognome.
     * @see Studente#aggiorniCorsoStudente() Per l'aggiornamento del corso.
     * @see Studente#aggiorniNumeroTelefono() Per l'aggiornamento del numero di telefono.
     * @see Studente#aggiorniEtà() Per l'aggiornamento dell'età.
     * @see Studente#aggiorniInsegnamentoStudente() Per l'aggiornamento degli insegnamenti dello studente.
     */
    public void modifichiStudente() {
        // Visualizza il menu delle operazioni di aggiornamento
        System.out.printf("Operazioni di aggiornamento : %n" +
                "1- Aggiorna ID studente%n" +
                "2- Aggiorna la password dello/a studente/ssa%n" +
                "3- Aggiorna il trimestre dello/a studente/ssa%n" +
                "4- Aggiorna il nome dello/a studente/ssa%n" +
                "5- Aggiorna il cognome dello/a studente/ssa %n" +
                "6- Aggiorna il corso dello/a studente/ssa%n" +
                "7- Aggiorna il numero di telefono dello/a studente/ssa%n" +
                "8- Aggiorna l'età dello/a studente/ssa%n" +
                "9- Aggiorna gli insegnamenti dello/a studente/ssa%n" +
                "10- Torna indietro%n" +
                "Scegli un'opzione, per favore. Cosa desideri aggiornare? ");

        // Ottieni l'opzione valida dall'utente
        int opzione = Main.opzioneValida(10);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Esegui l'aggiornamento corrispondente all'opzione scelta
        if (opzione == 1)
            aggiorniIdStudente();
        else if (opzione == 2)
            aggiorniPassword();
        else if (opzione == 3)
            aggiorniTrimestre();
        else if (opzione == 4)
            aggiorniNome();
        else if (opzione == 5)
            aggiorniCognome();
        else if (opzione == 6)
            aggiorniCorsoStudente();
        else if (opzione == 7)
            aggiorniNumeroTelefono();
        else if (opzione == 8)
            aggiorniEtà();
        else if (opzione == 9)
            aggiorniInsegnamentoStudente();
        else if (opzione == 10)
            return;

        // Dopo l'aggiornamento, richiama nuovamente il metodo per ulteriori modifiche o per tornare indietro nel menu.
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        modifichiStudente();
    }
    /**
     * Restituisce una lista degli insegnamenti a cui lo studente è iscritto.
     *
     * @return Una lista degli insegnamenti a cui lo studente è attualmente iscritto.
     */
    public ArrayList<Insegnamento> getInsegnamentiIscriti() {
        return new ArrayList<>(insegnamentiIscritti.keySet());
    }

    /**
     * Imposta gli insegnamenti a cui lo studente è iscritto.
     *
     * @param insegnamentiIscritti Una mappa che associa gli insegnamenti agli eventuali voti ottenuti.
     */
    public void setInsegnamentiIscritti(HashMap<Insegnamento ,Double> insegnamentiIscritti){
        this.insegnamentiIscritti = insegnamentiIscritti;
    }

    /**
     * Restituisce una lista degli insegnamenti disponibili per lo studente.
     * Gli insegnamenti disponibili sono quelli che appartengono al trimestre attuale dello studente
     * e a cui lo studente non è già iscritto.
     *
     * @return Una lista degli insegnamenti disponibili per lo studente, o null se non ce ne sono.
     */
    public ArrayList<Insegnamento> getInsegnamentiDisponibili() {
        // Ottiene tutti gli insegnamenti disponibili
        ArrayList<Insegnamento> availableInsegnamenti = new ArrayList<>(InsegnamentoController.getInsegnamenti());

        // Rimuove gli insegnamenti che non appartengono al trimestre attuale dello studente
        availableInsegnamenti.removeIf(insegnamento -> insegnamento.getTrimestre() != getTrimestre());

        // Rimuove gli insegnamenti a cui lo studente è già iscritto
        availableInsegnamenti.removeAll(getInsegnamentiIscriti());

        // Verifica se ci sono insegnamenti disponibili
        if (availableInsegnamenti.size() > 0)
            return availableInsegnamenti;
        else {
            System.out.printf("Non ci sono insegnamenti disponibili per questo studente%n");
            return null;
        }
    }



    /**
     * Consente all'utente di aggiornare gli insegnamenti dello studente.
     * Questa operazione offre diverse opzioni:
     * 1. Iscrizione a un insegnamento.
     * 2. Ritiro da un insegnamento.
     * 3. Aggiornamento del voto in un insegnamento.
     * 4. Tornare indietro al menu precedente.
     */
    public void aggiorniInsegnamentoStudente() {
        // Selezione dell'operazione per gli insegnamenti
        System.out.printf("Operazioni di aggiornamento insegnamenti dello studente: %n" +
                "1- Iscrizione a un insegnamento%n" +
                "2- Ritiro da un insegnamento%n" +
                "3- Aggiornamento del voto in un insegnamento%n" +
                "4- Tornare indietro%n" +
                "Per favore, scegli cosa desideri modificare per gli insegnamenti degli studenti: ");

        // Lettura dell'opzione scelta dall'utente
        int opzione = Main.opzioneValida(4);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Gestione delle operazioni
        if (opzione == 4)
            modifichiStudente();

        else {
            // Iscrizione a un insegnamento
            if (opzione == 1)
                iscrivitiAInsegnamento(InsegnamentoController.selezioniInsegnamento(getInsegnamentiDisponibili(), null), true);

                // Ritiro da un insegnamento
            else if (opzione == 2)
                ritiratiDaInsegnamento(InsegnamentoController.selezioniInsegnamento(getInsegnamentiIscriti(), this), true);

                // Aggiornamento del voto in un insegnamento
            else if (opzione == 3)
                aggiorniInsegnamentoTrimestre(InsegnamentoController.selezioniInsegnamento(getInsegnamentiIscriti(), this));

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Richiama il metodo di aggiornamento degli insegnamenti dello studente in modo ricorsivo
            aggiorniInsegnamentoStudente();
        }
    }
    /**
     * Consente allo studente di iscriversi a un insegnamento specifico.
     *
     * @param insegnamento L'insegnamento a cui lo studente vuole iscriversi.
     * @param admin        Un flag che indica se l'utente che sta eseguendo l'operazione è un amministratore.
     */
    public void iscrivitiAInsegnamento(Insegnamento insegnamento, boolean admin) {
        // Verifica se l'insegnamento esiste
        if (insegnamento != null) {
            double voto = 0;

            // Se l'utente è un amministratore, chiede di inserire il voto dello studente
            if (admin) {
                System.out.print("Inserisci il voto dello studente in questo insegnamento o -1 per annullare l'operazione: ");
                voto = Main.getNumeriDecimaliValidi();
                while (voto != -1 && (voto < insegnamento.getVotoMinimo() || voto > insegnamento.getVotoMassimo())) {
                    System.out.print("Scusa, il voto dello studente deve essere nell'intervallo [voto minimo indicato, voto massimo indicato]. Inserisci un voto valido o -1 per annullare l'operazione: ");
                    voto = Main.getNumeriDecimaliValidi();
                }
            }

            // Se l'operazione è stata annullata, mostra un messaggio appropriato
            if (voto == -1)
                System.out.printf("Operazione annullata!%n");
            else {
                // Registra lo studente nell'insegnamento e imposta il voto
                insegnamentiIscritti.put(insegnamento, voto);
                insegnamento.iscriviStudenteInInsegnamento(this);
                System.out.printf("L'iscrizione è stata effettuata con successo!%n");
            }
        }
    }
    /**
     * Ritira lo studente dall'insegnamento specificato.
     *
     * @param insegnamento L'insegnamento dal quale lo studente vuole ritirarsi.
     * @param flag         Un flag che indica se è necessario mostrare un messaggio di successo.
     */
    public void ritiratiDaInsegnamento(Insegnamento insegnamento, boolean flag) {
        if (insegnamento != null) {
            insegnamentiIscritti.remove(insegnamento);
            insegnamento.rimuoviStudenteDaInsegnamento(this);
            if (flag)  System.out.printf("Il ritiro è stato effettuato con successo!%n");
        }
    }

    /**
     * Ritira lo studente da tutti gli insegnamenti a cui è iscritto.
     * Questo metodo rimuove lo studente da tutti gli insegnamenti registrati e li notifica.
     */
    public void ritiratiDaTuttiInsegnamenti() {
        for (Insegnamento insegnamento : insegnamentiIscritti.keySet()) {
            insegnamento.rimuoviStudenteDaInsegnamento(this);
        }
        insegnamentiIscritti.clear();
    }
    /**
     * Restituisce il voto dello studente per un insegnamento specifico.
     *
     * @param insegnamento L'insegnamento per cui si desidera ottenere il voto.
     * @return Il voto dello studente per l'insegnamento specificato.
     */
    public double getVoto(Insegnamento insegnamento) {
        return insegnamentiIscritti.get(insegnamento);
    }

    /**
     * Imposta il voto dello studente per un insegnamento specifico.
     *
     * @param insegnamento L'insegnamento per cui si desidera impostare il voto.
     * @param voto         Il nuovo voto da assegnare allo studente per l'insegnamento.
     */
    public void setVoto(Insegnamento insegnamento, double voto) {
        insegnamentiIscritti.put(insegnamento, voto);
    }

    /**
     * Consente all'utente di aggiornare il voto dello studente per un insegnamento.
     * Se il nuovo voto è valido, sostituisce il voto precedente con il nuovo valore.
     *
     * @param insegnamento L'insegnamento per cui si desidera aggiornare il voto.
     */
    public void aggiorniInsegnamentoTrimestre(Insegnamento insegnamento) {
        if (insegnamento != null) {
            System.out.print("Inserisci un nuovo voto o digiti -1 per annullare l'operazione: ");
            double votoNuovo = Main.getNumeriDecimaliValidi();
            while (votoNuovo != -1 && (votoNuovo < insegnamento.getVotoMinimo() || votoNuovo > insegnamento.getVotoMassimo())) {
                System.out.print("Mi dispiace, il voto dello studente deve essere compreso nell'intervallo [voto minimo indicato, voto massimo indicato]. Inserisci un voto valido o -1 per annullare l'operazione: ");
                votoNuovo = Main.getNumeriDecimaliValidi();
            }
            if (votoNuovo == -1)
                System.out.printf("Operazione annullata!%n");
            else {
                setVoto(insegnamento, votoNuovo);
                System.out.printf("Voto aggiornato a %.2f%n", votoNuovo);
            }
        }
    }

    /**
     * Calcola la media dei voti dello studente in tutti gli insegnamenti iscritti.
     *
     * @return La media dei voti degli insegnamenti iscritti in percentuale.
     */
    public double calcoliMedia() {
        double media = 0;
        for (Map.Entry<Insegnamento, Double> entry : insegnamentiIscritti.entrySet()) {
            Insegnamento insegnamento = entry.getKey();
            double voto = entry.getValue();
            // Calcola la percentuale del voto rispetto al voto massimo dell'insegnamento
            media += ((voto / insegnamento.getVotoMassimo()) * 100);
        }
        // Restituisci la media se ci sono insegnamenti iscritti, altrimenti 0
        if (insegnamentiIscritti.size() == 0) return 0;
        return (media / insegnamentiIscritti.size());
    }

    /**
     * Stampa l'elenco degli insegnamenti a cui lo studente è iscritto con dettagli come ID insegnamento,
     * nome insegnamento, trimestre e voto dello studente.
     *
     * @param insegnamenti La lista degli insegnamenti da stampare.
     */
    public void stampiStudenteInsegnamenti(ArrayList<Insegnamento> insegnamenti) {
        if (insegnamenti != null && insegnamenti.size() > 0) {
            System.out.printf("Gli insegnamenti iscritti dallo/a studente/ssa: %n");
            System.out.printf("%-10s%-20s%-50s%-25s%-10s%-20s%n", "Indice", "Insegnamento ID", "Insegnamento Nome", "Insegnamento trimestre", "Voto", "Voto in percentuale");
            int i = 1;
            for (Insegnamento insegnamento : insegnamenti) {
                double voto = getVoto(insegnamento);
                // Calcola il voto in percentuale rispetto al voto massimo dell'insegnamento
                double votoPercentuale = (voto / insegnamento.getVotoMassimo()) * 100;
                System.out.printf("%-10s%-20s%-50s%-25s%-10s%-20s%n", i++, insegnamento.getId(), insegnamento.getNome(), insegnamento.getTrimestre(), voto, votoPercentuale);
            }
        } else {
            System.out.printf("Lo/a studente/ssa non si è iscritto/a a nessun insegnamento%n");
        }
    }

    /**
     * Stampa un resoconto completo dello studente, inclusi dettagli come ID studente, numero di trimestri,
     * nome, cognome, corso, numero di telefono, età e una lista degli insegnamenti a cui è iscritto
     * con i relativi voti e la media (se ci sono insegnamenti iscritti).
     */
    public void stampiResocontoCompleto() {
        System.out.printf("ID studente : %s%nN. di trimestre : %d%nNome : %s %s%nCorso : %s%nNumero di telefono : %s%nEtà : %d%n",
                getIdStudente(), getTrimestre(), getNome(),
                getCognome(), getCorso(), getNumeroTelefono(), getEtà());
        stampiStudenteInsegnamenti(getInsegnamentiIscriti());
        if (insegnamentiIscritti.size() > 0) {
            double media = calcoliMedia();
            System.out.printf("Media = %.2f%%%n", media);
        }
    }
}
