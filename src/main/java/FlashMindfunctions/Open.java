package FlashMindfunctions;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Open {
    public static void open(Scanner scanner, List<Card> cards) {
        String number;
        System.out.print("ID: ");
        number = scanner.nextLine();

        int idNumber = Integer.parseInt(number, 10);
        int maxId = cards.size();
        if (idNumber > maxId) {
            System.out.printf("\u001B[31mDiese ID gibt es nicht, die höchste ID ist %d%n\u001B[0m", cards.size());
        }

        Card currentCard = cards.stream()
                .filter(c -> c.id == idNumber)
                .findFirst()
                .orElse(null);

        if (currentCard != null) {
            System.out.printf("%-25s | %-95s%n", "Frage", "Antwort");
            System.out.println("------------------------------------");
            System.out.println(currentCard.question + " " + currentCard.answer);
        } else {
            System.out.println("Karte nicht gefunden");
        }
        System.out.print("Zum schliessen `close` schreiben: ");
        String close = scanner.nextLine();
        if (close.equals("close") || close.equals("Close")) {

            System.out.println("Deine vorhandenen Karten...");
            cards.sort(Comparator.comparing(card -> card.buildDate));

            System.out.printf("%-5s | %-50s | %-95s%n", "ID", "Frage", "Erstellt am");
            System.out.println("------------------------------------------------------------------------");

            for (int ks = 0; ks < cards.size(); ks++) {
                currentCard = cards.get(ks);
                if (!currentCard.question.equals(" ")) {
                    System.out.printf("%-5s | %-50s | %-95s%n",
                            currentCard.id,
                            currentCard.question,
                            currentCard.buildDate.toString());
                }
            }
        } else {
            System.out.println("\u001B[31mGib close zum schliessen ein(ALLES KLEIN!)\u001B[0m");
        }
    }
}
