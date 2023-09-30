
# Introduzione

Prima di leggere questo, per favore, legga il file 'README PRINCIPALE.md', che contiene anche una procedura dettagliata su come aprire e seguire questi programmi.

Il programma "Russell-Newton V1.0" visualizza i dati degli studenti e dei professori dell'istituto Russell-Newton, fornendo anche il calcolo delle entrate, delle uscite e, infine, del bilancio annuale dello stesso istituto.

Per questo programma, non è richiesto all'utente di inserire input tramite tastiera. Invece, il programma funziona autonomamente una volta avviato.

Per personalizzare l'output (cioè i risultati visualizzati a schermo), è necessario apportare modifiche nella classe principale (Main) del programma. In altre parole, è necessario modificare il codice o gestire le operazioni nella classe Main.

![Pasted image 20230928204144](https://github.com/gianni-jin14/Russell-Newton-V1.0-in-Java/assets/129873947/40c90f56-8409-403f-a0f1-baf571f6046c)



# Pagina Main

## Sintesi

Questa è la classe principale del programma. Il costruttore della classe è vuoto, il che significa che non esegue alcuna operazione specifica alla creazione di un oggetto “Main”.

---

## Metodi

### Metodo Main

Il metodo “main”: è il metodo principale del programma. Viene eseguito quando si avvia il programma Java. Ecco cosa fa il metodo “main”:

- **Creazione di oggetti "Professore”**: Vengono creati tre oggetti “Professore” (Gianni, Letizia e Melissa) con i rispettivi ID, nomi e stipendi. Questi oggetti rappresentano i professori della scuola.
- **Creazione di una lista di "Professori”**: Viene creata una lista denominata “listaProfessori” che contiene i professori creati in precedenza. Questa lista viene utilizzata per gestire e tenere traccia dei professori della scuola.
- **Creazione di oggetti "Studente”**: Vengono creati cinque oggetti “Studente” (Matteo, Paola, Bianca, Chiara e Antonio) con i rispettivi ID, nomi e classi. Questi oggetti rappresentano gli studenti iscritti alla scuola.
- **Creazione di una lista di "Studenti”**: Viene creata una lista denominata “listaStudenti” che contiene gli studenti creati in precedenza. Questa lista viene utilizzata per gestire e tenere traccia degli studenti della scuola.
- **Creazione di un oggetto "Scuola”**: Viene creato un oggetto “Scuola” chiamato “istitutoRussellNewton” utilizzando le liste di professori e studenti create in precedenza. Questo oggetto rappresenta la scuola e consente di gestire le informazioni relative ai professori, agli studenti e alle operazioni finanziarie dell'istituto.
- **Pagamento delle tasse scolastiche degli studenti**: Vengono effettuati i pagamenti delle tasse scolastiche da parte degli studenti (“paola”, “bianca”, “matteo”, “chiara” e “antonio”). I pagamenti vengono registrati utilizzando il metodo “paghiTasse” degli oggetti “Studente”, e gli importi pagati vengono aggiunti agli incassi totali della scuola utilizzando il metodo “aggiorniIncassiTotali” della classe “Scuola”.
- **Stampa dei dati finanziari dell'istituto scolastico**: Vengono stampati i dati finanziari dell'istituto, inclusi gli incassi totali, il finanziamento fornito alla scuola e il bilancio totale della scuola utilizzando i metodi “getIncassiTotali”, “getFinanziamento” e “getBilancio” della classe “Scuola”.
- **Stampa delle operazioni di pagamento dei salari dei professori**: Vengono effettuati i pagamenti dei salari ai professori (“gianni” e “melissa”) utilizzando il metodo “paghiSalario” degli oggetti “Professore”. Le operazioni di pagamento vengono stampate insieme all'importo dell'incasso totale della scuola utilizzando “getRal”, “getNomeProf” e “getBilancio” della classe “Professore” e della classe “Scuola”.
- **Stampa del bilancio annuale della scuola**”: Viene stampato il bilancio annuale della scuola utilizzando il metodo “getBilancio” della classe “Scuola”.


---


# Classe Scuola

## Sintesi
La classe “Scuola” gestisce le informazioni relative ai professori, agli studenti e al bilancio della scuola. Fornisce metodi per aggiungere professori e studenti, nonché per aggiornare gli incassi, i costi e il bilancio della scuola
.
## Variabili

Nella classe Scuola, per quanto riguarda le variabili, le variabili “professori” e “studenti” sono variabili di istanza di tipo List.  Rappresentano le liste dei professori e degli studenti presenti nella scuola. Le liste contengono oggetti delle classi “Professore” e “Studente”.

Poi abbiamo le variabili statiche "incassiTotali", costiTotali, finanziamento” e “bilancio”: Queste sono variabili statiche di tipo “int” che mantengono il totale degli incassi, il totale dei costi, il finanziamento fornito alla scuola e il bilancio totale della scuola. Essendo variabili statiche, condividono lo stesso valore tra tutte le istanze della classe “Scuola”.

Il termine "bilancio" rappresenta la differenza tra la somma degli "incassiTotali" e il "finanziamento", sottratta dai "costiTotali". In un contesto privato, questo valore sarebbe comunemente denominato "profitto". Tuttavia, nel contesto del settore pubblico, ho scelto di utilizzare il termine "bilancio" per riflettere la specifica situazione finanziaria in questo ambito.

---

## Costruttore

Dopo la dichiarazione delle variabili, abbiamo il costruttore "Scuola".

Accetta due argomenti: una lista di professori e una lista di studenti. Quando viene creato un oggetto “Scuola”, il costruttore inizializza le variabili “professori” e “studenti” con le liste fornite. Inoltre, imposta le variabili statiche “incassiTotali” a 0, “costiTotali” a 0, “finanziamento” a 100000 e “bilancio” a 0.

---


## Metodi

### Metodi get per restituire valori delle variabili

I metodi get della classe sono i seguenti:

- “Metodo “getProfessore””: Questo metodo restituisce la lista dei professori presenti nella scuola. È un metodo di accesso per la variabile “professori”.
- “Metodo “getStudente””: Questo metodo restituisce la lista degli studenti iscritti alla scuola. È un metodo di accesso per la variabile “studenti”.
- “Metodo “getIncassiTotali””: Questo metodo restituisce l'ammontare totale degli incassi della scuola.
- “Metodo “getFinanziamento””: Questo metodo restituisce l'ammontare del finanziamento fornito alla scuola.
- “Metodo “getCostiTotali””: Questo metodo restituisce l'ammontare totale dei costi della scuola.
- “Metodo “getBilancio””: Questo metodo restituisce il bilancio totale della scuola.

---


### Metodi per aggiungere valori alla lista
Dopo i vari metodi get come getProfessore o getStudente che restituiscono il valore delle rispettive variabili, abbiamo anche alcuni metodi per aggiungere valori alla lista.

- “Metodo “aggiungiProfessore””: Questo metodo permette di aggiungere un professore alla lista dei professori della scuola. Richiede un oggetto “Professore” come argomento e lo aggiunge alla lista “professori”.
- “Metodo “aggiungiStudente””: Questo metodo permette di aggiungere uno studente alla lista degli studenti iscritti alla scuola. Richiede un oggetto “Studente” come argomento e lo aggiunge alla lista “studenti”.

---

### Metodi per aggiornare valori dei variabili

Infine abbiamo alcuni metodi per aggiornare degli alcuni variabili dichiarati:

- “Metodo statico “aggiorniIncassiTotali””: Questo metodo permette di aggiornare l'ammontare totale degli incassi della scuola. Richiede un importo di incasso da aggiungere agli incassi totali e lo aggiorna. Inoltre, chiama il metodo “aggiorniBilancio” per aggiornare il bilancio totale.
- “Metodo statico “aggiorniCostiTotali””: Questo metodo permette di aggiornare l'ammontare totale dei costi della scuola. Richiede un importo di costi da aggiungere ai costi totali e li aggiorna. Inoltre, chiama il metodo “aggiorniBilancio” per aggiornare il bilancio totale.
- “Metodo statico “aggiorniBilancio””: Questo metodo permette di aggiornare il bilancio totale della scuola in base agli incassi e ai costi totali. Richiede un importo di risultato da aggiungere al bilancio e lo aggiorna. Questo metodo viene chiamato dai metodi “aggiorniIncassiTotali” e “aggiorniCostiTotali” per mantenere il bilancio aggiornato.


---


# Classe Studente
## Sintesi
La classe “Studente” rappresenta uno studente all'interno di un istituto scolastico, con informazioni personali come nome, classe e stato del pagamento delle tasse scolastiche. Fornisce metodi per impostare la classe, registrare il pagamento delle tasse e ottenere informazioni sullo studente.

---

## Variabili
I variabili della classe Studente sono “idStudente”, “nomeStudente”, “classe”, “tasseScolastichePagate”, e “tasseScolasticheDovute””
Queste sono variabili di istanza che rappresentano le informazioni personali di uno studente. In particolare:
- “idStudente” è un intero che rappresenta l'ID univoco dello studente.
- “nomeStudente” è una stringa che rappresenta il nome dello studente.
- “classe” è una stringa che rappresenta la classe di appartenenza dello studente.
- “tasseScolastichePagate” è un intero che rappresenta l'importo delle tasse scolastiche già pagate dallo studente.
- “tasseScolasticheDovute” è un intero che rappresenta l'importo totale delle tasse scolastiche dovute dallo studente.


---

## Costruttore
Il costruttore “Studente”” accetta tre argomenti: “idStudente”, “nomeStudente”, e “classe”. Quando viene creato un oggetto “Studente”, il costruttore inizializza le variabili “idStudente”, “nomeStudente”, e “classe” con i valori forniti. Inoltre, inizializza “tasseScolastichePagate” a 0 e “tasseScolasticheDovute” a 106.




---


## Metodi

### Metodi get per restituire valori delle variabili
Metodi di accesso della classe sono “getIdStudente”, “getNomeStudente”, “getClasse”, “getTasseScolastichePagate”, “getTasseScolasticheDovute”, e “getTasseDovuteRimanenti”

Questi metodi permettono di ottenere i valori delle variabili private dello studente. Ad esempio, “getIdStudente” restituisce l'ID dello studente.

---

### Metodo set per impostare i valori delle variabili
“Metodo “setClasse””: permette di impostare la classe di appartenenza dello studente. Richiede una stringa “classe” come argomento e imposta la variabile “classe” con il valore fornito.

---

### Altri metodi
Il Metodo "paghiTasse"  registra il pagamento delle tasse scolastiche da parte dello studente. Richiede un importo “tasse” come argomento e lo aggiunge all'importo delle tasse pagate “tasseScolastichePagate”. Inoltre, chiama il metodo “aggiorniIncassiTotali” della classe “Scuola” per aggiornare gli incassi totali della scuola con l'importo delle tasse pagate.

Il metodo “toString”  restituisce una rappresentazione testuale dello studente, includendo il nome e l'importo delle tasse scolastiche pagate. È utile per la visualizzazione dei dettagli dello studente.

---



# Classe Professore

## Sintesi

La classe “Professore” rappresenta un professore o una professoressa in una scuola, con informazioni relative al loro ruolo e stipendio. Fornisce metodi per impostare lo stipendio, registrare il pagamento dello stipendio e ottenere informazioni sul professore.

---

## Variabili
Le variabili della classe Professore sono le seguenti:

“idProf”, “nomeProf”, “ral” e “ralTotale”” sono variabili di istanza e variabili statiche che rappresentano le informazioni relative al professore. 
In particolare:
- “idProf” è un intero che rappresenta l'ID univoco del professore.
-  “nomeProf” è una stringa che rappresenta il nome del professore.
- “ral” è una variabile statica che rappresenta lo stipendio per i professori.
- “ralTotale” è una variabile che rappresenta l'ammontare totale guadagnato dai professori.


---

## Costruttore
Per quanto riguarda il costruttore, il costruttore “Professore” accetta tre argomenti: “idProf”, “nomeProf”, e “ral”. Quando viene creato un oggetto “Professore”, il costruttore inizializza le variabili “idProf” e “nomeProf” con i valori forniti. Inoltre, inizializza la variabile “ral” (stipendio fisso per tutti i professori) con il valore fornito e inizializza “ralTotale” a 0.

---
## Metodi
### Metodi get per restituire valori delle variabili
Poi ci sono metodi di accesso come “getIdProf”, “getNomeProf”, e “getRal”

Questi metodi permettono di ottenere i valori delle variabili private del professore. Ad esempio, “getIdProf” restituisce l'ID del professore.

---

### Metodo set per impostare il valori della variabili

Successivamente, il metodo “setRal” permette di impostare un nuovo stipendio fisso (“ral”) per tutti i professori. Modifica la variabile “ral” con il nuovo valore fornito.

---
### Alcuni metodi particolari
Infine, abbiamo alcuni metodi particolari:

Il metodo “paghiSalario" registra il pagamento dello stipendio al professore e aggiorna l'ammontare totale dei costi della scuola (“ralTotale” e “costiTotali” della classe “Scuola”). Richiede un importo “ral” come argomento e lo aggiunge all'importo totale guadagnato da tutti i professori (“ralTotale”). Inoltre, chiama il metodo “aggiorniCostiTotali” della classe “Scuola” per aggiornare i costi totali della scuola con l'importo dello stipendio pagato.

Il metodo “toString" restituisce una rappresentazione testuale del professore, includendo il nome del professore e l'ammontare totale guadagnato. È utile per la visualizzazione dei dettagli del professore.


