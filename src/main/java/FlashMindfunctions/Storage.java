package FlashMindfunctions;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    public static void saveCardsToFile(Path path, List<Card> cards) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write("[\n");

            for (int i = 0; i < cards.size(); i++) {

                Card card = cards.get(i);

                writer.write("{\n");
                writer.write("\"id\": " + card.id + ",\n");
                writer.write("\"question\": \"" + card.question + "\",\n");
                writer.write("\"answer\": \"" + card.answer.replace("}", "\\}").replace("{", "\\{") + "\",\n");
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
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der JSON-Datei: " + e.getMessage());
        }
    }
}
