package FlashMindfunctions;

import java.util.List;

public class ShowAllMethod {
    public static void showAll(List<Card> cards) {

        for (int ks = 0; ks < cards.size(); ks++) {
            Card currentCard = cards.get(ks);
            if (!currentCard.question.equals(" ")) {

                System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s | %-15s | %-30s%n",
                        currentCard.id,
                        currentCard.question,
                        currentCard.buildDate.toString(),
                        currentCard.counter,
                        currentCard.correctCounter,
                        currentCard.falseCounter,
                        currentCard.lastLearn
                );

            }
        }
    }
}
