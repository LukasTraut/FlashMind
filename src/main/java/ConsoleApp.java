
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.Scanner;

@QuarkusMain
public class ConsoleApp implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World!");
        System.out.println("Geben Sie 'exit' ein, um das Programm zu beenden:");

        Scanner scanner = new Scanner(System.in);
        String input;

        // Endlosschleife bis "exit" eingegeben wird
        do {
            System.out.print("> ");
            input = scanner.nextLine();
        } while (!"exit".equals(input));

        System.out.println("Programm wird beendet...");
        scanner.close();

        return 0; // Erfolgreiche Beendigung
    }

    public static void main(String[] args) {
        Quarkus.run(ConsoleApp.class, args);
    }
}

