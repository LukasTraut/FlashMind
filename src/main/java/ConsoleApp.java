import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.time.LocalDate;
import java.util.*;

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



        while (true) {
            System.out.println("learn = Lernkarte lernen / show all = Alle anzeigen / exit = Programm schliessen");

            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input) || "Exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
                return 0;
            }

            if ("learn".equals(input) || "Learn".equals(input)) {
                String numberLearn;
                System.out.print("ID: ");
                numberLearn = scanner.nextLine();
                int idNumber = Integer.parseInt(numberLearn);


                Card currentCard = null;
                for (Card c : cards) {
                    if (c.id == idNumber) {
                        currentCard = c;
                        break;
                    }
                }
                int maxId = cards.size()-1;
                 if(idNumber > maxId) {
                    System.out.printf("\u001B[31mEs sind nur %d Karten vorhanden.%n\u001B[0m", cards.size());
                    continue;

                }

                if ( !currentCard.question.trim().equals("")) {
                    System.out.println("Frage: " + currentCard.question);
                    System.out.print("Antwort: ");
                    Scanner newLearnAnswer = new Scanner(System.in);
                    String answer1 = newLearnAnswer.nextLine();
                    if (answer1.length() <= 251) {
                        if (!answer1.equals(currentCard.answer)) {
                            System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer );
                        } else {
                            System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                        }
                    }
                    else{
                        System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                    }
                }

            }

            if ("show all".equals(input) || "Show all".equals(input)|| "Show All".equals(input)){

                System.out.println("Deine vorhandenen Karten...");
                cards.sort(Comparator.comparing(card -> card.buildDate));

                System.out.printf("%-5s | %-50s | %-95s%n", "ID", "Frage", "Erstellt am");
                System.out.println("------------------------------------------------------------------------");

                for (int ks = 0; ks < cards.size(); ks++) {
                    Card currentCard = cards.get(ks);
                    if (!currentCard.question.equals(" ")) {
                        System.out.printf("%-5s | %-50s | %-95s%n",
                                currentCard.id,
                                currentCard.question,
                                currentCard.buildDate.toString());
                    }
                }
            }

            if( !"exit".equals(input) && !"Exit".equals(input) && !"show all".equals(input) && !"Show all".equals(input) && !"learn".equals(input) && !"Learn".equals(input)) {

                System.out.println("\u001B[31mBefehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.\u001B[0m");
            }
        }
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}