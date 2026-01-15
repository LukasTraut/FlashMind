package FlashMindfunctions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadCards {
    public static List<Card> loadCardsFromFile(Path path) {
        List<Card> cards = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            StringBuilder json = new StringBuilder();
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }

            String content = json.toString().trim();
            content = content.substring(1, content.length() - 1);
            String[] objects = content.split("},\\s*\\{");

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
                    String[] keyValue = field.split("(?<!\\\\):");
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

                Card card = new Card(question, answer, buildDate, counter, correctCounter, falseCounter, lastLearn);
                card.id = id;
                cards.add(card);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der JSON-Datei: " + e.getMessage());
            System.exit(1);
        }

        return cards;
    }
}
