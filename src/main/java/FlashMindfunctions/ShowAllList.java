package FlashMindfunctions;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static FlashMindfunctions.EditList.editList;
import static FlashMindfunctions.Delete.delete;

public class ShowAllList {
    public void showAllList(Scanner scanner, List<Card> cards, Path flashCard) {
        System.out.println("Deine vorhandenen Karten...");

        System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s | %-15s | %-30s%n", "ID", "Frage", "Erstellt am", "Counter", "Richtig gelernt", "Falsch gelernt", "Letztes mal gelernt am");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int ks = 0; ks < cards.size(); ks++) {
            Card currentCard = cards.get(ks);
            if (!currentCard.question.equals(" ")) {


                double correctPercent = 0.0; // primitive double
                double falsePercent = 0.0;

                if (currentCard.counter > 0) {
                    // ACHTUNG: mind. ein double in der Rechnung, damit kein int-division!
                    correctPercent = (currentCard.correctCounter * 100.0) / currentCard.counter;
                    falsePercent = (currentCard.falseCounter * 100.0) / currentCard.counter;

                    // Runden auf 1 Nachkommastelle: 1.29 -> 1.3
                    correctPercent = Math.round(correctPercent * 10.0) / 10.0;
                    falsePercent = Math.round(falsePercent * 10.0) / 10.0;
                }


                System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s | %-15s | %-30s%n",
                        currentCard.id,
                        currentCard.question,
                        currentCard.buildDate.toString(),
                        currentCard.counter,
                        correctPercent + "%",
                        falsePercent + "%",
                        currentCard.lastLearn
                );

            }
        }
        editList(scanner, cards, flashCard);
        delete(scanner, cards, flashCard);
    }
}
