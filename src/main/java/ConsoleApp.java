import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.time.LocalDate;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;





@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    static int nextid = 0;

    public class Card{

        int id;
        String question;
        String answer;
        LocalDate builddate;

        public Card(String question, String answer, String dateString) {
            this.id = nextid++;
            this.question = question;
            this.answer = answer;
            this.builddate = LocalDate.parse(dateString);
        }


    }

    @Override
    public int run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Wann wurde Frankfurt gegründet?", "1899", "2025-09-04"));
        cards.add(new Card("Wie heisst die schönste Stadt auf der Welt?", "Frankfurt", "2025-09-05"));
        cards.add(new Card("Wann wurde Eintracht Frankfurt Deutscher Meister?", "1959", "2025-04-05"));



        while (true) {
            System.out.println("open = Öffnen einer Lernkarte / show all = Alle anzeigen / exit = Programm schliessen");

            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input) || "Exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
                return 0;
            }



            if ("show all".equals(input) || "Show all".equals(input)|| "Show All".equals(input)){

                System.out.println("Deine vorhandenen Karten...");
                cards.sort(Comparator.comparing(card -> card.builddate));

                System.out.printf("%-5s | %-50s | %-95s%n", "ID", "Frage", "Erstellt am");
                System.out.println("------------------------------------------------------------------------");

                for (int ks = 0; ks < cards.size(); ks++) {
                    Card aktuelleKarte = cards.get(ks);
                    if (!aktuelleKarte.question.equals(" ")) {
                        System.out.printf("%-5s | %-50s | %-95s%n",
                                aktuelleKarte.id,
                                aktuelleKarte.question,
                                aktuelleKarte.builddate.toString());
                    }
                }
            }

            if ("open".equals(input)|| "Open".equals(input)) {

                String number;
                System.out.print("ID: ");
                number = scanner.nextLine();

                int idNumber = Integer.parseInt(number);
                if (idNumber >= cards.size()) {
                    System.out.printf("Es sind nur %d Karten vorhanden.%n", cards.size());
                    continue;
                }

                Card aktuelleKarte = null;
                for (Card c : cards) {
                    if (c.id == idNumber) {
                        aktuelleKarte = c;
                        break;
                    }
                }

                if (!aktuelleKarte.question.equals(" ")) {
                    System.out.printf("%-25s | %-95s%n", "Frage", "Antwort");
                    System.out.println("------------------------------------");
                    System.out.println(aktuelleKarte.question + " " + aktuelleKarte.answer);
                }


                        System.out.print("Zum schliessen `close` schreiben: ");
                        String close = scanner.nextLine();
                            if (close.equals("close")|| close.equals("Close")) {

                                System.out.println("Deine vorhandenen Karten...");
                                cards.sort(Comparator.comparing(card -> card.builddate));

                                System.out.printf("%-5s | %-50s | %-95s%n", "ID", "Frage", "Erstellt am");
                                System.out.println("------------------------------------------------------------------------");

                                for (int ks = 0; ks < cards.size(); ks++) {
                                     aktuelleKarte = cards.get(ks);
                                    if (!aktuelleKarte.question.equals(" ")) {
                                        System.out.printf("%-5s | %-50s | %-95s%n",
                                                aktuelleKarte.id,
                                                aktuelleKarte.question,
                                                aktuelleKarte.builddate.toString());
                                    }
                                }
                }
                            else {
                                System.out.println("Gib close zum schliessen ein(ALLES KLEIN!)");
                            }
            }
            if( !"exit".equals(input) && !"Exit".equals(input) && !"show all".equals(input) && !"Show all".equals(input) && !"open".equals(input) && !"Open".equals(input) && !"learn".equals(input) && !"Learn".equals(input)) {

                System.out.println("Befehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.");
            }
        }
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}