import org.junit.jupiter.api.Test;
import ru.shift.classifier.DataClassifier;
import ru.shift.enums.DataType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataClassifierTest {

    @Test
    public void shouldReturnInteger_WhenPositiveNumber() {
        assertEquals(DataType.INTEGER, DataClassifier.getType("123"));
    }

    @Test
    public void shouldReturnInteger_WhenNegativeNumber() {
        assertEquals(DataType.INTEGER, DataClassifier.getType("-456"));
    }

    @Test
    public void shouldReturnInteger_WhenZero() {
        assertEquals(DataType.INTEGER, DataClassifier.getType("0"));
    }

    @Test
    public void shouldReturnInteger_WhenVeryLargeNumber() {
        assertEquals(DataType.INTEGER, DataClassifier.getType("12345678901234567890"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalNumber() {
        assertEquals(DataType.FLOAT, DataClassifier.getType("123.456"));
    }

    @Test
    public void shouldReturnFloat_WhenNegativeDecimal() {
        assertEquals(DataType.FLOAT, DataClassifier.getType("-456.789"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalWithoutLeadingZero() {
        assertEquals(DataType.FLOAT, DataClassifier.getType(".789"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalWithLeadingZero() {
        assertEquals(DataType.FLOAT, DataClassifier.getType("0.123"));
    }

    @Test
    public void shouldReturnString_WhenPlainText() {
        assertEquals(DataType.STRING, DataClassifier.getType("text"));
    }

    @Test
    public void shouldReturnString_WhenMixedLettersAndNumbers() {
        assertEquals(DataType.STRING, DataClassifier.getType("123abc"));
    }

    @Test
    public void shouldReturnString_WhenEmptyInput() {
        assertEquals(DataType.STRING, DataClassifier.getType(""));
    }

    @Test
    public void shouldReturnString_WhenInputWithSpaces() {
        assertEquals(DataType.STRING, DataClassifier.getType(" 123 "));
    }

    @Test
    public void shouldReturnString_WhenSpecialSymbols() {
        assertEquals(DataType.STRING, DataClassifier.getType("@#$%"));
    }

    @Test
    public void shouldReturnString_WhenMultipleDots() {
        assertEquals(DataType.STRING, DataClassifier.getType("123.456.789"));
    }

    @Test
    public void shouldReturnString_WhenScientificNotation() {
        assertEquals(DataType.FLOAT, DataClassifier.getType("1.23e-4"));
    }

    @Test
    public void shouldReturnString_WhenNullInput() {
        assertEquals(DataType.STRING, DataClassifier.getType(null));
    }
}
