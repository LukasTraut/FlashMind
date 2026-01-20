package FlashMindfunctions;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortBy {
    public static void sortBy(List<Card> cards) {
        Scanner sortBy = new Scanner(System.in);
        System.out.println("Sortieren nach: sort id/ sort builddate/ sort best/ sort worst/ sort latest/ sort refresh");

        switch (sortBy.nextLine()) {
            case "sort id":
                cards.sort(Comparator.comparing(card -> card.id));
                Collections.reverse(cards);
                break;
            case "sort builddate":
                cards.sort(Comparator.comparing(card -> card.buildDate));
                Collections.reverse(cards);
                break;
            case "sort best":
                cards.sort(Comparator.comparing(card -> card.correctCounter));
                break;
            case "sort worst":
                cards.sort(Comparator.comparing(card -> card.falseCounter));
                Collections.reverse(cards);
                break;
            case "sort latest":
                cards.sort(Comparator.comparing(card -> card.lastLearn));
                Collections.reverse(cards);
                break;
            case "sort refresh":
                cards.sort(Comparator.comparing(card -> card.lastLearn));
                break;
            default:
                System.out.println("\u001B[31mSortierbefehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.\u001B[0m");
        }
    }
}
