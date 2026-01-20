package FlashMindfunctions;

import java.nio.file.Path;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;

import static FlashMindfunctions.EditMethod.editMethod;

public class Delete {
    public static void delete(Scanner scanner, List<Card> cards, Path flashCard) {
        Scanner deleteScanner = new Scanner(System.in);
        System.out.println("Delete zum Löschen einer Lernkarte/ Enter zum fortfahren");
        String delete = deleteScanner.nextLine();
        if ("delete".equals(delete) || "Delete".equals(delete)) {
            String deleteId;
            System.out.print("ID: ");
            deleteId = scanner.nextLine();
            int idDelete = Integer.parseInt(deleteId);

            Scanner finalDelete = new Scanner(System.in);
            System.out.println("Willst du die Lernkarte wirklich löschen? Yes/ No");

            String yesOrNo = finalDelete.nextLine();
            if ("yes".equals(yesOrNo) || "Yes".equals(yesOrNo)) {

                boolean removed = cards.removeIf(c -> c.id == idDelete);

                if (removed) {
                    Storage.saveCardsToFile(flashCard, cards);
                    System.out.println("Lernkarte (ID " + idDelete + ") gelöscht und Datei aktualisiert.");
                } else {
                    OptionalInt maxId = cards.stream().mapToInt(c -> c.id).max();
                    int maxDeleteId = maxId.getAsInt();
                    System.out.println("Diese ID gibt es nicht, die höchste ID ist " + maxDeleteId);
                }
            } else {
                System.out.println("Lernkarte wurde nicht gelöscht");
            }
        }
    }
}
