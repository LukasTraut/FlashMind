package FlashMindfunctions;

import java.time.LocalDate;
import java.util.Scanner;

import java.FlashMindfunctions.ConsoleApp;

public class Learn {
    String numberLearn;
                    System.out.print("ID: ");
    numberLearn =scanner.nextLine();
    int idNumber = Integer.parseInt(numberLearn);


    ConsoleApp.Card currentCard = null;
                    for(
    ConsoleApp.Card c :cards)

    {
        if (c.id == idNumber) {
            currentCard = c;
            break;
        }
    }

    int maxId = currentCard.id;
                    if(idNumber >maxId)

    {
        System.out.printf("\u001B[31mDiese ID gibt es nicht, die höchste ID ist %d%n\u001B[0m", cards.size());
        continue;

    }

                    if(!currentCard.question.equals(" "))

    {
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
