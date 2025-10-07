import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.time.LocalDate;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    static int nextID = 0;

    public class Card{
        int ID;
        String question;
        String answer;
        LocalDate builddate;

        public Card(String question, String answer, String dateString) {
            this.ID = nextID++;
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
        cards.add(new Card("UF", "97", "2025-04-05"));



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



            if ("show all".equals(input) || "Show all".equals(input)){

                System.out.println("Deine vorhandenen Karten...");
                cards.sort(Comparator.comparing(card -> card.builddate));

                for (int ks = 0; ks < cards.size(); ks++) {
                    Card aktuelleKarte = cards.get(ks);
                    if (!aktuelleKarte.question.equals(" ")) {
                        System.out.println(aktuelleKarte.ID + " " + aktuelleKarte.question + " " + aktuelleKarte.builddate);
                    }
                }
            }

            if ("open".equals(input)) {

                String number;
                System.out.print("ID: ");
                number = scanner.nextLine();
                int IDNumber = Integer.parseInt(number);

                Card aktuelleKarte = cards.get(IDNumber);
                if (!aktuelleKarte.question.equals(" ")) {
                    System.out.println(aktuelleKarte.question + " " + aktuelleKarte.answer);
                }


                        System.out.print("Zum schliessen `close` schreiben: ");
                        String close = scanner.nextLine();
                            if (close.equals("close")) {

                                System.out.println("Deine vorhandenen Karten...");
                                cards.sort(Comparator.comparing(card -> card.builddate));
                             for (int ks = 0; ks < cards.size(); ks++) {
                                  aktuelleKarte = cards.get(ks);
                                 if (!aktuelleKarte.question.equals(" ")) {
                                    System.out.println(aktuelleKarte.ID + " " + aktuelleKarte.question + " " + aktuelleKarte.builddate);
                        }
                    }
                }
                            else {
                                System.out.println("Gib close zum schliessen ein(ALLES KLEIN!)");
                            }
            }

        }
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}