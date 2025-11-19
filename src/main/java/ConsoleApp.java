
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

@QuarkusMain
public class ConsoleApp {

    static int nextid = 0;

    public class Card {
       public int id;
       public String question;
       public String answer;
       public LocalDate builddate;
       public Integer counter;
       public Boolean correct;

        public Card(String question, String answer, String dateString, Integer counter, Boolean correct ) {
            this.id = nextid++;
            this.question = question;
            this.answer = answer;
            this.builddate = LocalDate.parse(dateString);
            this.counter = counter;
            this.correct = correct;
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
            content = content.substring(1, content.length() -1);
            String[] objects = content.split("\\},\\s*\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();
                String[] fields = obj.split(",");

                int id = 0;
                String question = "";
                String answer = "";
                String builddate = "";
                int counter = 0;
                boolean correct = false;

                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    if (key.equals("id")) id = Integer.parseInt(value);
                    if (key.equals("question")) question = value;
                    if (key.equals("answer")) answer = value;
                    if (key.equals("builddate")) builddate = value;
                    if (key.equals("counter")) counter = Integer.parseInt(value);
                    if (key.equals("correct")) correct = Boolean.parseBoolean(value);
                }

                Card card = new ConsoleApp().new Card(question, answer, builddate, counter, correct);
                card.id = id;
                cards.add(card);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der JSON-Datei: " + e.getMessage());
        }

        return cards;
    }

    public static void saveCardsToFile(File file, List<Card> cards) {
        try (FileWriter writer = new FileWriter(file)){

            writer.write("[\n");

            for (int i = 0; i < cards.size(); i++) {

                Card card = cards.get(i);

                writer.write("{\n");
                writer.write("\"id\": " + card.id + ",\n");
                writer.write("\"question\": \"" + card.question + "\",\n");
                writer.write("\"answer\": \"" + card.answer + "\",\n");
                writer.write("\"builddate\": \"" + card.builddate + "\",\n");
                writer.write("\"counter\": " + card.counter + ",\n");
                writer.write("\"correct\": " + card.correct + "\n");
                writer.write("},\n");

                if (i > cards.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Erfolgreich gespeichert");
        }
        catch (IOException e) {
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
            String numberlearn;
            System.out.print("ID: ");
            numberlearn = scanner.nextLine();
            int idNumber = Integer.parseInt(numberlearn);


            Card currentCard = null;
            for (Card c : cards) {
                if (c.id == idNumber) {
                    currentCard = c;
                    break;
                }
            }
            int maxid = cards.size();
            if(idNumber > maxid) {
                System.out.printf("\u001B[31mEs sind nur %d Karten vorhanden.%n\u001B[0m", cards.size());
                continue;

            }

            if (!currentCard.question.equals(" ")) {
                System.out.println("Frage: " + currentCard.question);
                System.out.print("Antwort: ");
                Scanner newlernantwort = new Scanner(System.in);
                String antwort1 = newlernantwort.nextLine();
                if (antwort1.length() <= 251) {
                    if (!antwort1.equals(currentCard.answer)) {
                        System.out.println("\u001B[31mDie Antwort ist: \u001B[0m" + currentCard.answer );
                        currentCard.correct = false;
                        currentCard.counter++;
                        saveCardsToFile(flashCard, cards);
                    } else {
                        System.out.println("\u001B[32mDie Antwort ist richtig\u001B[0m");
                        currentCard.correct = true;
                        currentCard.counter++;
                        saveCardsToFile(flashCard, cards);
                    }
                }
                else{
                    System.out.println("\u001B[31mZu lang, maximal 250 Zeichen!!!!\u001B[0m");
                    currentCard.correct = false;
                    currentCard.counter++;
                    saveCardsToFile(flashCard, cards);
                }
            }

        }

        if ("show all".equals(input) || "Show all".equals(input)|| "Show All".equals(input)){

            System.out.println("Deine vorhandenen Karten...");
            cards.sort(Comparator.comparing(card -> card.builddate));

            System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s%n", "ID", "Frage", "Erstellt am", "Counter", "Richtig gelernt");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");

            for (int ks = 0; ks < cards.size(); ks++) {
                Card currentCard = cards.get(ks);
                if (!currentCard.question.equals(" ")) {
                    System.out.printf("%-5s | %-50s | %-20s | %-15s | %-15s%n",
                            currentCard.id,
                            currentCard.question,
                            currentCard.builddate.toString(),
                            currentCard.counter,
                            currentCard.correct);

                }
            }
        }

        if( !"exit".equals(input) && !"Exit".equals(input) && !"show all".equals(input) && !"Show all".equals(input) && !"learn".equals(input) && !"Learn".equals(input)) {

            System.out.println("\u001B[31mBefehl nicht erkannt! Bitte überprüfe die Schreibweise und versuche es erneut.\u001B[0m");
        }
    }
}}


