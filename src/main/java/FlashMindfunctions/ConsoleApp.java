package FlashMindfunctions;

import io.quarkus.runtime.annotations.QuarkusMain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import java.util.Scanner;


@QuarkusMain
public class ConsoleApp {


    public static void main(String[] args) {
        boolean startRandomContinue = true;
        Scanner scanner = new Scanner(System.in);

        Path flashCard = Paths.get("src/main/resources/FlashMindsCards/FlashMindsKarten.json");

        List<Card> cards = LoadCards.loadCardsFromFile(flashCard);
        while (true) {

            int lastRandomIndex = 0;

            while (true) {
                System.out.println("learn = Lernkarte lernen / start random = Zufälliges Lernen/ add = Lernkarte hinzufügen/ show all = Alle anzeigen/ open = Öffnen einer Lernkarte / exit = Programm schliessen");

                String input;


                System.out.print("> ");
                input = scanner.nextLine();

                if ("exit".equals(input) || "Exit".equals(input)) {

                    System.out.println("Programm wird beendet...");
                }

                if ("learn".equals(input) || "Learn".equals(input)) {
                    Learn learn = new Learn();
                    learn.startLearning(scanner, cards, flashCard);
                }

                if ("show all".equals(input) || "Show all".equals(input) || "Show All".equals(input)) {

                    SortBy.sortBy(cards);

                    ShowAllList showAllList = new ShowAllList();
                    showAllList.showAllList(scanner, cards, flashCard);


                }

                if ("Open".equals(input) || "open".equals(input)) {

Open.open(scanner, cards);
                }

                if ("add".equals(input) || "Add".equals(input)) {

                    System.out.println("Gib eine ID ein");
                    System.out.print("> ");
                    String idAddstring = scanner.nextLine();
                    int idAdd = Integer.parseInt(idAddstring);
                    System.out.println("Gib eine Frage ein");
                    System.out.print("> ");
                    String questionAdd = scanner.nextLine();
                    System.out.println("Gib die Antwort ein");
                    System.out.print("> ");
                    String answerAdd = scanner.nextLine();

                    System.out.println("Hinzugefügte Frage: " + questionAdd + " / Hinzugefügte Antwort: " + answerAdd);

                    Add.addCard(cards, idAdd, questionAdd, answerAdd, flashCard, scanner);

                }
                if ("Start Random".equals(input) || "Start random".equals(input) || "start random".equals(input)) {

                    Random.startRandom(startRandomContinue,lastRandomIndex,scanner,cards, flashCard);

                }
                if (!"exit".equals(input) && !"Exit".equals(input) && !"show all".equals(input) && !"Show all".equals(input) && !"open".equals(input) && !"Open".equals(input) && !"FlashMindfunctions.learn".equals(input) && !"Learn".equals(input) && !"Start Random".equals(input) && !"Start random".equals(input) && !"start random".equals(input)) {

                    System.out.println("\u001B[31mBefehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.\u001B[0m");
                }
            }
        }
    }
}
