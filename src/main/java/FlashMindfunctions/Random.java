package FlashMindfunctions;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Random {
    public static void startRandom(boolean startRandomContinue, int lastRandomIndex, Scanner scanner, List<Card> cards, Path flashCard) {
        int randomTries = 1;
        do {
            if (randomTries <= 1) {
                java.util.Random rand = new java.util.Random();

                int randomIndex;
                do {
                    randomIndex = rand.nextInt(cards.size());
                } while (randomIndex == lastRandomIndex);

                Card currentCard = cards.get(randomIndex);
                lastRandomIndex = randomIndex;

                if (!currentCard.question.trim().equals("")) {
                    System.out.println("Frage: " + currentCard.question);
                    System.out.print("Antwort: ");
                    Scanner newLearnAnswer = new Scanner(System.in);
                    String answer1 = newLearnAnswer.nextLine();
                    if (answer1.length() <= 251) {
                        if (!answer1.equals(currentCard.answer)) {
                            System.out.println("\u001B[31mDie Antwort war leider falsch, die richtige Antwort ist: \u001B[0m" + currentCard.answer);
                            currentCard.falseCounter++;
                            currentCard.counter++;
                            currentCard.lastLearn = LocalDate.now();
                            Storage.saveCardsToFile(flashCard, cards);
                        } else {
                            System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                            currentCard.correctCounter++;
                            currentCard.counter++;
                            currentCard.lastLearn = LocalDate.now();
                            Storage.saveCardsToFile(flashCard, cards);
                        }
                    } else {
                        System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                        currentCard.falseCounter++;
                        currentCard.counter++;
                        currentCard.lastLearn = LocalDate.now();
                        Storage.saveCardsToFile(flashCard, cards);
                    }
                }
                randomTries++;
            } else {

                System.out.println("`stop random` zum beenden enter drücken zum fortfahren");
                String randomContinue;
                randomContinue = scanner.nextLine();

                if ("Stop Random".equals(randomContinue) || "Stop random".equals(randomContinue) || "stop random".equals(randomContinue)) {
                    startRandomContinue = false;
                    System.out.println("\u001B[32mStart Random geschlossen\u001B[0m");
                    break;
                } else if (!randomContinue.trim().equals("")) {
                    System.out.println("\u001B[31mBefehl nicht erkannt! Bitte überprüfe deine Schreibweise auf `stop random` und versuche es erneut.\u001B[0m");
                } else {
                    java.util.Random rand = new java.util.Random();

                    int randomIndex;
                    do {
                        randomIndex = rand.nextInt(cards.size());
                    } while (randomIndex == lastRandomIndex);

                    Card currentCard = cards.get(randomIndex);
                    lastRandomIndex = randomIndex;

                    if (!currentCard.question.trim().equals("")) {
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
                                Storage.saveCardsToFile(flashCard, cards);
                            } else {
                                System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                                currentCard.correctCounter++;
                                currentCard.counter++;
                                currentCard.lastLearn = LocalDate.now();
                                Storage.saveCardsToFile(flashCard, cards);
                            }
                        } else {
                            System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                            currentCard.falseCounter++;
                            currentCard.counter++;
                            currentCard.lastLearn = LocalDate.now();
                            Storage.saveCardsToFile(flashCard, cards);
                        }
                    }
                    randomTries++;
                }
            }
        } while (startRandomContinue == true);
    }
}
