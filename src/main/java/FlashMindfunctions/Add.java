package FlashMindfunctions;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Add {
    public static void addCard(List<Card> cards, int idAdd, String questionAdd, String answerAdd, Path flashCard, Scanner scanner) {
        System.out.println("Bestätigen Save / Edit / Cancel");
        Scanner save = new Scanner(System.in);
        String saved = save.nextLine();

        if ("Save".equals(saved) || "save".equals(saved)) {

            for (Card c : cards) {
                if (c.id == idAdd) {
                    System.out.println("Diese ID ist bereits vergeben.");
                    return;
                }
            }

            Card newCard = new Card();
            newCard.id = idAdd;
            newCard.question = questionAdd;
            newCard.answer = answerAdd;
            newCard.buildDate = java.time.LocalDate.now();
            newCard.counter = 0;
            newCard.correctCounter = 0;
            newCard.falseCounter = 0;
            newCard.lastLearn = java.time.LocalDate.now();

            cards.add(newCard);

            Storage.saveCardsToFile(flashCard, cards);
            System.out.println("Lernkarte wurde gespeichert");

            System.out.println("Deine vorhandenen Karten...");

            System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s | %-15s | %-30s%n", "ID", "Frage", "Erstellt am", "Counter", "Richtig gelernt", "Falsch gelernt", "Letztes mal gelernt am");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

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
            }}
        else if ("Edit".equals(saved) || "edit".equals(saved)) {

            System.out.println("Aktuelle Frage: " + questionAdd);
            String newQuestion = scanner.nextLine();

            System.out.println("Aktuelle Antwort: " + answerAdd);
            String newAnswer = scanner.nextLine();

            System.out.println("Neue Frage: " + newQuestion + " / Neue Antwort: " +  newAnswer);

            addCard(cards, idAdd, newQuestion, newAnswer, flashCard, scanner);
        }
        else {
            System.out.println("Lernkarte wurde nicht gespeichert");

        }
    }
}
