
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.Scanner;

@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        String antwort = "";
        String lernkarte = "";
        while (true) {
            System.out.println("h = Hinzufügen/ l = Lernen/ show all = Alle anzeigen/ exit = Programm schliessen");

            Scanner scanner = new Scanner(System.in);
            String input;


            System.out.print("> ");
            input = scanner.nextLine();

            if ("exit".equals(input)) {

                System.out.println("Programm wird beendet...");
                scanner.close();
                return 0;
            }


            if ("h".equals(input)) {

                System.out.println("Gib eine Frage ein");
                System.out.print("> ");
                Scanner newlernkarte = new Scanner(System.in);
                 lernkarte = newlernkarte.nextLine();

                System.out.println("Gib die Antwort ein");
                System.out.print("> ");
                Scanner newantwort = new Scanner(System.in);
                 antwort = newantwort.nextLine();

                System.out.println("Lernkarte wurde mit der Frage " + lernkarte + " hinzugefügt");
            }

            if ("l".equals(input)) {
                System.out.println("Gib die Antwort von der Frage " + lernkarte + " ein");
                System.out.print("> ");
                Scanner newlernantwort = new Scanner(System.in);
                 lernkarte = newlernantwort.nextLine();
                    if (!lernkarte.equals(antwort)) {
                System.out.println("Die Antwort ist" + antwort);}
                    else {
                        System.out.println("Die Antwort ist richtig");
                    }
            }


            if ("show all".equals(input)) {

                System.out.println("Deine vorhandenen Karten...");
            }
        }
    }


    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}

