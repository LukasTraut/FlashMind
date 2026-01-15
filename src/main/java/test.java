import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {

    @Test
    void parseCommand_mapsGermanAndEnglishVariants() {
        FlashMind fm = new FlashMind();
        assertEquals(FlashMind.Command.RANDOM, fm.parseCommand("start random"));
        assertEquals(FlashMind.Command.RANDOM, fm.parseCommand("zufälliges lernen"));
        assertEquals(FlashMind.Command.EXIT, fm.parseCommand("exit"));
        assertEquals(FlashMind.Command.UNKNOWN, fm.parseCommand("foobar"));
    }

}