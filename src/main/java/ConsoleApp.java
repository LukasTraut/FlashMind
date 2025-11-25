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

    static int nextId = 0;

    public class Card{
        int id;
        String question;
        String answer;
        LocalDate buildDate;

        public Card(String question, String answer, String dateString) {
            this.id = nextId++;
            this.question = question;
            this.answer = answer;
            this.buildDate = LocalDate.parse(dateString);
        }


    }

    @Override
    public int run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Wann wurde Frankfurt gegründet?", "1899", "2025-09-04"));
        cards.add(new Card("Wie heisst die schönste Stadt auf der Welt?", "Frankfurt", "2025-09-05"));
        cards.add(new Card("Wann wurde Eintracht Frankfurt Deutscher Meister?", "1959", "2025-04-05"));

        int lastRandomIndex  = 0;

        while (true) {
            System.out.println("start random = Zufälliges lernen/ show all = Alle anzeigen/ exit = Programm schliessen");

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
                cards.sort(Comparator.comparing(card -> card.buildDate));

                for (int ks = 0; ks < cards.size(); ks++) {
                    Card currentCard = cards.get(ks);
                    if (!currentCard.question.equals(" ")) {
                        System.out.println(currentCard.id + " " + currentCard.question + " " + currentCard.buildDate);
                    }
                }
            }


            if ("Start Random".equals(input) || "Start random".equals(input) || "start random".equals(input)) {

                boolean continuing = true;
                int randomTries  = 1;

                do {
                    if (randomTries  <= 1){
                        Random rand = new Random();

                        int randomIndex;
                        do {
                            randomIndex = rand.nextInt(cards.size());
                        } while (randomIndex == lastRandomIndex );

                        Card currentCard = cards.get(randomIndex);
                        lastRandomIndex  = randomIndex;

                        if (!currentCard.question.equals(" ")) {
                            System.out.println("Frage: " + currentCard.question);
                            System.out.print("Antwort: ");
                            Scanner newLearnAnswer = new Scanner(System.in);
                            String answer1 = newLearnAnswer.nextLine();
                            if (answer1.length() <= 251) {
                                if (!answer1.equals(currentCard.answer)) {
                                    System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer);
                                } else {
                                    System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                                }
                            } else {
                                System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                            }}
                        randomTries++;}
                    else {

                    System.out.println("`stop random` zum beenden enter drücken zum fortfahren  ");
                    String randomContinue;
                        randomContinue = scanner.nextLine();

                    if ("Stop Random".equals(randomContinue) || "Stop random".equals(randomContinue) || "stop random".equals(randomContinue)) {
                        continuing = false;
                    } else {
                        Random rand = new Random();

                        int randomIndex;
                        do {
                            randomIndex = rand.nextInt(cards.size());
                        } while (randomIndex == lastRandomIndex );

                        Card currentCard = cards.get(randomIndex);
                        lastRandomIndex  = randomIndex;

                        if (!currentCard.question.equals(" ")) {
                            System.out.println("Frage: " + currentCard.question);
                            System.out.print("Antwort: ");
                            Scanner newLearnAnswer = new Scanner(System.in);
                            String answer1 = newLearnAnswer.nextLine();
                            if (answer1.length() <= 251) {
                                if (!answer1.equals(currentCard.answer)) {
                                    System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer);
                                } else {
                                    System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                                }
                            } else {
                                System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                            }
                        }
                        randomTries ++;
                    }
                }}
                while (continuing == true);


            }
        }
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}