package FlashMindfunctions;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static FlashMindfunctions.ShowAllMethod.showAll;

public class EditMethod {
    public static void editMethod(List<Card> cards, Scanner scanner, Path flashCard, int idEdit, String newQuestion, String newAnswer) {

        Card currentCard = null;
        for (Card c : cards) {
            if (c.id == idEdit) {
                currentCard = c;
                break;
            }
        }

        System.out.println("Aktuelle Frage: " + newQuestion);
        newQuestion = scanner.nextLine();

        System.out.println("Aktuelle Antwort: " + newAnswer);
        newAnswer = scanner.nextLine();

        System.out.println("Neue Frage: " + newQuestion + " / Neue Antwort: " + newAnswer);

        System.out.print("Aktion (save / edit / cancel): ");
        Scanner aprove = new Scanner(System.in);
        String aproved = aprove.nextLine();
        if ("save".equals(aproved) || "Save".equals(aproved)) {
            currentCard.question = newQuestion;
            currentCard.answer = newAnswer;
            Storage.saveCardsToFile(flashCard, cards);
            System.out.println("Lernkarte (ID " + idEdit + ") geändert und Datei aktualisiert.");

            showAll(cards);
        } else if ("Edit".equals(aproved) || "edit".equals(aproved)) {
            editMethod(cards, scanner, flashCard, idEdit, newQuestion, newAnswer);
        } else {
            System.out.println("Lernkarte wurde nicht geändert");

            showAll(cards);
        }

    }
}
