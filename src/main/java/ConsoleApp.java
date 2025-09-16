
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.Arrays;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;


@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    public class Karte{
        String frage;
        String antwort;
        String erstellungsdatum;


        public Karte(String frage, String antwort, String erstellungsdatum) {
            this.frage = frage;
            this.antwort = antwort;
            this.erstellungsdatum = erstellungsdatum;
        }
    }

    @Override
    public int run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        List<Karte> karten = new ArrayList<>();
        karten.add(new Karte("Wann wurde Frankfurt gegründet?", "1899", "4.9.25"));
        karten.add(new Karte("Wie heisst die schönste Stadt auf der Welt?", "Frankfurt", "5.9.25"));

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

