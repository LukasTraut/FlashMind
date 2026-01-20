package FlashMindfunctions;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static FlashMindfunctions.Storage.saveCardsToFile;

public class Learn {

    public void startLearning(Scanner scanner, List<Card> cards, Path flashCard) {
        String numberLearn;
        System.out.println("ID: ");
        numberLearn = scanner.nextLine();
        int idNumber = Integer.parseInt(numberLearn);

        Card currentCard = null;
        for (Card c : cards) {
            if (c.id == idNumber) {
                currentCard = c;
                break;
            }
        }

        int maxId = currentCard.id;
        if (idNumber > maxId) {
            System.out.printf("\u001B[31mDiese ID gibt es nicht, die höchste ID ist %d%n\u001B[0m", cards.size());
        }

        if (!currentCard.question.equals(" ")) {
            System.out.println("Frage: " + currentCard.question);
            System.out.print("Antwort: ");
            Scanner newLearnAnswer = new Scanner(System.in);
            String answer1 = newLearnAnswer.nextLine();
            if (answer1.length() <= 251) {
                if (!answer1.equals(currentCard.answer)) {
                    System.out.println("\u001B[31mDie Antwort war leider falsch, die richtige antwort ist: \u001B[0m" + currentCard.answer);
                    currentCard.falseCounter++;
                    currentCard.counter++;
                    currentCard.lastLearn = LocalDate.now();
                    saveCardsToFile(flashCard, cards);
                } else {
                    System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                    currentCard.correctCounter++;
                    currentCard.counter++;
                    currentCard.lastLearn = LocalDate.now();
                    saveCardsToFile(flashCard, cards);
                }
            } else {
                System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                currentCard.falseCounter++;
                currentCard.counter++;
                currentCard.lastLearn = LocalDate.now();
                saveCardsToFile(flashCard, cards);
            }
        }

    }
}
