
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta il programma principale dell'istituto scolastico.
 * Qui vengono creati e gestiti professori, studenti e le operazioni finanziarie dell'istituto.
 */
public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        // Creazione di tre oggetti professore con rispettivi ID, nomi e stipendi
        Professore gianni = new Professore(1, "Gianni", 30000);
        Professore letizia = new Professore(2, "Letizia", 28000);
        Professore melissa = new Professore(3, "Melissa", 34000);

        // Creazione di una lista contenente i professori
        List<Professore> listaProfessori = new ArrayList();
        listaProfessori.add(gianni);
        listaProfessori.add(letizia);
        listaProfessori.add(melissa);

        // Creazione di cinque oggetti studente con rispettivi ID, nomi e classi
        Studente matteo = new Studente(1, "Matteo", "3C MODA");
        Studente paola = new Studente(2, "Paola", "3C MODA");
        Studente antonio = new Studente(3, "Antonio", "1B MODA");
        Studente chiara = new Studente(3, "Chiara", "1B MODA");
        Studente bianca = new Studente(3, "Bianca", "1B MODA");

        // Creazione di una lista contenente gli studenti
        List<Studente> listaStudenti = new ArrayList();
        listaStudenti.add(matteo);
        listaStudenti.add(paola);
        listaStudenti.add(bianca);
        listaStudenti.add(chiara);
        listaStudenti.add(antonio);

        // Creazione di un oggetto Scuola con le liste di professori e studenti
        Scuola istitutoRussellNewton = new Scuola(listaProfessori, listaStudenti);

        // Pagamento delle tasse scolastiche da parte degli studenti
        paola.paghiTasse(130);
        bianca.paghiTasse(130);
        matteo.paghiTasse(130);
        chiara.paghiTasse(130);
        antonio.paghiTasse(130);

        // Stampa dei dati finanziari dell'istituto scolastico
        System.out.println("Istituto_Russell_Newton ha un ricavo totale di " + istitutoRussellNewton.getIncassiTotali() + "€");
        System.out.println("Inoltre, la scuola riceve un finanziamento dallo stato pari a " + istitutoRussellNewton.getFinanziamento() + "€");
        System.out.println("La risorsa finanziaria a disposizione della scuola è di " + istitutoRussellNewton.getBilancio() + "€");

        // Stampa delle operazioni di pagamento dei salari dei professori
        System.out.println("------PAGO I MIEI SALARI----");
        gianni.paghiSalario(gianni.getRal());
        PrintStream var10000 = System.out;
        int var10001 = gianni.getRal();
        var10000.println("Istituto_Russell_Newton ha speso " + var10001 + " per " + gianni.getNomeProf() + " e ha un incasso totale di " + istitutoRussellNewton.getBilancio());
        melissa.paghiSalario(melissa.getRal());
        var10000 = System.out;
        var10001 = melissa.getRal();
        var10000.println("Istituto_Russell_Newton ha speso " + var10001 + " per " + melissa.getNomeProf() + " e ha un incasso totale di " + istitutoRussellNewton.getBilancio());

        // Stampa del bilancio annuale della scuola
        System.out.println("Il bilancio annuale della scuola è " + istitutoRussellNewton.getBilancio());

        // Stampa delle informazioni sugli studenti
        System.out.println(bianca);
        letizia.paghiSalario(letizia.getRal());
        System.out.println(letizia);
    }
}
