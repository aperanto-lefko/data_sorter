import org.junit.jupiter.api.Test;
import ru.shift.ArgumentParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArgumentParserTest {
    @Test
    void shouldThrowException_WhenNoArgumentsProvided() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(new String[]{});
        });
    }

    @Test
    void shouldParseInputFiles_WhenOnlyFilesSpecified() {
        String[] args = {"file1.txt", "file2.txt"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals(2, parser.getInputFiles().size());
        assertTrue(parser.getInputFiles().contains("file1.txt"));
        assertTrue(parser.getInputFiles().contains("file2.txt"));
        assertEquals(".", parser.getOutputDir());
        assertEquals("", parser.getPrefix());
        assertFalse(parser.isAppend());
        assertFalse(parser.isFullStats());
    }

    @Test
    void shouldParseOutputOption_WhenOSpecified() {
        String[] args = {"-o", "output_dir", "input.txt"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals("output_dir", parser.getOutputDir());
        assertEquals(1, parser.getInputFiles().size());
        assertEquals("input.txt", parser.getInputFiles().get(0));
    }

    @Test
    void shouldThrowException_WhenOMissingValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(new String[]{"-o"});
        });
    }

    @Test
    void shouldParsePrefixOption_WhenPSpecified() {
        String[] args = {"-p", "prefix_", "data.csv"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals("prefix_", parser.getPrefix());
        assertEquals(1, parser.getInputFiles().size());
    }

    @Test
    void shouldSetAppendFlag_WhenASpecified() {
        String[] args = {"-a", "file.log"};
        ArgumentParser parser = new ArgumentParser(args);

        assertTrue(parser.isAppend());
        assertEquals(1, parser.getInputFiles().size());
    }

    @Test
    void shouldSetStatsFlag_WhenSSpecified() {
        String[] args = {"-s", "report.txt"};
        ArgumentParser parser = new ArgumentParser(args);

        assertTrue(parser.isFullStats());
        assertEquals(1, parser.getInputFiles().size());
    }

    @Test
    void shouldThrowException_WhenUnknownOption() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(new String[]{"-x", "file.txt"});
        });
    }

    @Test
    void shouldThrowException_WhenNoInputFiles() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(new String[]{"-o", "out", "-a"});
        });
    }

    @Test
    void shouldParseAllOptionsTogether() {
        String[] args = {"-o", "output", "-p", "pref", "-a", "-s", "data1.csv", "data2.csv"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals("output", parser.getOutputDir());
        assertEquals("pref", parser.getPrefix());
        assertTrue(parser.isAppend());
        assertTrue(parser.isFullStats());
        assertEquals(2, parser.getInputFiles().size());
        assertTrue(parser.getInputFiles().contains("data1.csv"));
        assertTrue(parser.getInputFiles().contains("data2.csv"));
    }
}
