
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.nio.channels.ScatteringByteChannel;
import java.time.LocalDate;
import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@QuarkusMain
public class ConsoleApp {

    static int nextId = 0;

    public class Card {
        public int id;
        public String question;
        public String answer;
        public LocalDate buildDate;
        public Integer counter;
        public Integer correctCounter;
        public Integer falseCounter;
        public LocalDate lastLearn;


        public Card(String question, String answer, String dateString, Integer counter, Integer correctCounter, Integer falseCounter, String lastDate) {
            this.id = nextId++;
            this.question = question;
            this.answer = answer;
            this.buildDate = LocalDate.parse(dateString);
            this.counter = counter;
            this.correctCounter = correctCounter;
            this.falseCounter = falseCounter;
            this.lastLearn = LocalDate.parse(lastDate);
        }
    }


    public static List<Card> loadCardsFromFile(File file) {
        List<Card> cards = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder json = new StringBuilder();
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }

            String content = json.toString().trim();
            content = content.substring(1, content.length() - 1);
            String[] objects = content.split("\\},\\s*\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();
                String[] fields = obj.split(",");

                int id = 0;
                String question = "";
                String answer = "";
                String buildDate = "";
                int counter = 0;
                int correctCounter = 0;
                int falseCounter = 0;
                String lastLearn = "";

                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    if (key.equals("id")) id = Integer.parseInt(value);
                    if (key.equals("question")) question = value;
                    if (key.equals("answer")) answer = value;
                    if (key.equals("builddate")) buildDate = value;
                    if (key.equals("counter")) counter = Integer.parseInt(value);
                    if (key.equals("correctcounter")) correctCounter = Integer.parseInt(value);
                    if (key.equals("falsecounter")) falseCounter = Integer.parseInt(value);
                    if (key.equals("lastlearn")) lastLearn = value;
                }

                Card card = new ConsoleApp().new Card(question, answer, buildDate, counter, correctCounter, falseCounter, lastLearn);
                card.id = id;
                cards.add(card);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der JSON-Datei: " + e.getMessage());
        }

        return cards;
    }

    public static void saveCardsToFile(File file, List<Card> cards) {
        try (FileWriter writer = new FileWriter(file)) {

            writer.write("[\n");

            for (int i = 0; i < cards.size(); i++) {

                Card card = cards.get(i);

                writer.write("{\n");
                writer.write("\"id\": " + card.id + ",\n");
                writer.write("\"question\": \"" + card.question + "\",\n");
                writer.write("\"answer\": \"" + card.answer + "\",\n");
                writer.write("\"builddate\": \"" + card.buildDate + "\",\n");
                writer.write("\"counter\": " + card.counter + ",\n");
                writer.write("\"correctcounter\": " + card.correctCounter + ",\n");
                writer.write("\"falsecounter\": " + card.falseCounter + ",\n");
                writer.write("\"lastlearn\": \"" + card.lastLearn + "\"\n");
                writer.write("}\n");

                if (i < cards.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Erfolgreich gespeichert");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der JSON-Datei: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        File flashCard = new File("src/main/resources/FlashMindsCards/FlashMindsKarten.json");

        List<Card> cards = loadCardsFromFile(flashCard);

        while (true) {

            System.out.println("learn = Lernkarte lernen / show all = Alle anzeigen / exit = Programm schliessen");

            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input) || "Exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
            }

            if ("learn".equals(input) || "Learn".equals(input)) {
                String numberLearn;
                System.out.print("ID: ");
                numberLearn = scanner.nextLine();
                int idNumber = Integer.parseInt(numberLearn);


                Card currentCard = null;
                for (Card c : cards) {
                    if (c.id == idNumber) {
                        currentCard = c;
                        break;
                    }
                }
                int maxId = cards.size();
                if (idNumber > maxId) {
                    System.out.printf("\u001B[31mEs sind nur %d Karten vorhanden.%n\u001B[0m", cards.size());
                    continue;

                }

                if (!currentCard.question.equals(" ")) {
                    System.out.println("Frage: " + currentCard.question);
                    System.out.print("Antwort: ");
                    Scanner newlearnanswer = new Scanner(System.in);
                    String answer1 = newlearnanswer.nextLine();
                    if (answer1.length() <= 251) {
                        if (!answer1.equals(currentCard.answer)) {
                            System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer);
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

            if ("show all".equals(input) || "Show all".equals(input) || "Show All".equals(input)) {

                Scanner sortby = new Scanner(System.in);
                System.out.println("Sortieren nach: sort id/ sort builddate/ sort best/ sort worst/ sort latest/ sort refresh");

                switch (sortby.nextLine()) {
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
                }
            }

            if (!"exit".equals(input) && !"Exit".equals(input) && !"show all".equals(input) && !"Show all".equals(input) && !"learn".equals(input) && !"Learn".equals(input)) {

                System.out.println("\u001B[31mBefehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.\u001B[0m");
            }
        }
    }
}


