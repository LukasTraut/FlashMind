package FlashMindfunctions;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static FlashMindfunctions.EditMethod.editMethod;

public class EditList {
    public static void editList(Scanner scanner, List<Card> cards, Path flashCard) {
        Scanner editScanner = new Scanner(System.in);
        System.out.println("Edit zum bearbeiten einer Lernkarte/ Enter zum fortfahren");
        String edit = editScanner.nextLine();

        if ("Edit".equals(edit) || "edit".equals(edit)) {
            System.out.print("ID: ");
            String editId = scanner.nextLine();
            int idEdit = Integer.parseInt(editId);

            Card currentCard = null;
            for (Card c : cards) {
                if (c.id == idEdit) {
                    currentCard = c;
                    break;
                }
            }

            String newQuestion = currentCard.question;
            String newAnswer = currentCard.answer;
            editMethod(cards, scanner, flashCard, idEdit, newQuestion, newAnswer);
        }
    }
}