
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.time.LocalDate;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    static int nächsteID = 1;

    public class Karte{
        int ID;
        String frage;
        String antwort;
        LocalDate erstellungsdatum;

        public Karte(String frage, String antwort, String datumString) {
            this.ID = nächsteID++;
            this.frage = frage;
            this.antwort = antwort;
            this.erstellungsdatum = LocalDate.parse(datumString);
        }


    }

    @Override
    public int run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        List<Karte> karten = new ArrayList<>();
        karten.add(new Karte("Wann wurde Frankfurt gegründet?", "1899", "2025-09-04"));
        karten.add(new Karte("Wie heisst die schönste Stadt auf der Welt?", "Frankfurt", "2025-09-05"));
        karten.add(new Karte("UF", "97", "2025-04-05"));

         int i= 0;

        while (true) {
            System.out.println("h = Hinzufügen/ l = Lernen/ show all = Alle anzeigen/ exit = Programm schliessen");

            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
                return 0;
            }


            if ("h".equals(input)) {

                System.out.println("Diese Funktion ist noch nicht möglich!!!");


            }
            if ("show all".equals(input)){

                System.out.println("Deine vorhandenen Karten...");
                karten.sort(Comparator.comparing(karte -> karte.erstellungsdatum));

                for (int ks = 0; ks < karten.size(); ks++) {
                    Karte aktuelleKarte = karten.get(ks);
                    if (!aktuelleKarte.frage.equals(" ")) {
                        System.out.println(aktuelleKarte.frage + " " + aktuelleKarte.antwort + " " + aktuelleKarte.erstellungsdatum);
                    }
                }
            }

            if ("l".equals(input)) {

                if (i < karten.size()) {
                    Karte aktuelleKarte = karten.get(i);
                    System.out.println("Gib die Antwort auf die Frage: " + aktuelleKarte.frage);
                    System.out.print("> ");
                    String antwort = scanner.nextLine();

                    if (!antwort.equals(aktuelleKarte.antwort)) {
                        System.out.println("Die Antwort ist: " + aktuelleKarte.antwort);
                    } else {
                        System.out.println("Die Antwort ist richtig!");
                        i++;
                    }

                }



            }
        }}

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}

