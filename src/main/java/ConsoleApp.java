import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ConsoleApp implements QuarkusApplication {
    @Override

    public int run(String... args) throws Exception {
        if (args.length > 0) {
            System.out.println("Hello " + args[0]);
        } else {
            System.out.println("Hello World");
        }
        return 0;
    }}
