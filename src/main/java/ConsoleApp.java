import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.time.LocalDate;
import java.util.*;
import java.util.Random;
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
        cards.add(new Card("UF", "97", "2025-04-05"));

        int comparisonid = 0;

        while (true) {
            System.out.println("random = Zufälliges lernen/ show all = Alle anzeigen/ exit = Programm schliessen");

            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
                return 0;
            }


            if ("show all".equals(input)) {

                System.out.println("Deine vorhandenen Karten...");
                cards.sort(Comparator.comparing(card -> card.builddate));

                for (int ks = 0; ks < cards.size(); ks++) {
                    Card aktuelleKarte = cards.get(ks);
                    if (!aktuelleKarte.question.equals(" ")) {
                        System.out.println(aktuelleKarte.id + " " + aktuelleKarte.question + " " + aktuelleKarte.builddate);
                    }
                }
            }


            if ("random".equals(input)) {


                Random rand = new Random();
                int randomIdNumber = rand.nextInt(cards.size());

                if (randomIdNumber == comparisonid) {
                     rand = new Random();
                     comparisonid = rand.nextInt(cards.size());
                }

else{
                Card currentCard = null;
                for (Card c : cards) {
                    if (c.id == randomIdNumber) {
                        currentCard = c;
                    }
                }


                if (!currentCard.question.equals(" ")) {
                    System.out.println("Frage: " + currentCard.question);
                    System.out.print("Antwort: ");
                    Scanner newlernantwort = new Scanner(System.in);
                    String antwort1 = newlernantwort.nextLine();
                    if (antwort1.length() <= 251) {
                        if (!antwort1.equals(currentCard.answer)) {
                            System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer);
                        } else {
                            System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                    }
                }
                comparisonid = randomIdNumber;
            }
        }
        }
    
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}