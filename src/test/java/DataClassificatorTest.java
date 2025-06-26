import org.junit.jupiter.api.Test;
import ru.shift.DataClassificator;
import ru.shift.DataType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataClassificatorTest {

    @Test
    public void shouldReturnInteger_WhenPositiveNumber() {
        assertEquals(DataType.INTEGER, DataClassificator.getType("123"));
    }

    @Test
    public void shouldReturnInteger_WhenNegativeNumber() {
        assertEquals(DataType.INTEGER, DataClassificator.getType("-456"));
    }

    @Test
    public void shouldReturnInteger_WhenZero() {
        assertEquals(DataType.INTEGER, DataClassificator.getType("0"));
    }

    @Test
    public void shouldReturnInteger_WhenVeryLargeNumber() {
        assertEquals(DataType.INTEGER, DataClassificator.getType("12345678901234567890"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalNumber() {
        assertEquals(DataType.FLOAT, DataClassificator.getType("123.456"));
    }

    @Test
    public void shouldReturnFloat_WhenNegativeDecimal() {
        assertEquals(DataType.FLOAT, DataClassificator.getType("-456.789"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalWithoutLeadingZero() {
        assertEquals(DataType.FLOAT, DataClassificator.getType(".789"));
    }

    @Test
    public void shouldReturnFloat_WhenDecimalWithLeadingZero() {
        assertEquals(DataType.FLOAT, DataClassificator.getType("0.123"));
    }

    @Test
    public void shouldReturnString_WhenPlainText() {
        assertEquals(DataType.STRING, DataClassificator.getType("text"));
    }

    @Test
    public void shouldReturnString_WhenMixedLettersAndNumbers() {
        assertEquals(DataType.STRING, DataClassificator.getType("123abc"));
    }

    @Test
    public void shouldReturnString_WhenEmptyInput() {
        assertEquals(DataType.STRING, DataClassificator.getType(""));
    }

    @Test
    public void shouldReturnString_WhenInputWithSpaces() {
        assertEquals(DataType.STRING, DataClassificator.getType(" 123 "));
    }

    @Test
    public void shouldReturnString_WhenSpecialSymbols() {
        assertEquals(DataType.STRING, DataClassificator.getType("@#$%"));
    }

    @Test
    public void shouldReturnString_WhenMultipleDots() {
        assertEquals(DataType.STRING, DataClassificator.getType("123.456.789"));
    }

    @Test
    public void shouldReturnString_WhenScientificNotation() {
        assertEquals(DataType.STRING, DataClassificator.getType("1.23e-4"));
    }

    @Test
    public void shouldReturnString_WhenNullInput() {
        assertEquals(DataType.STRING, DataClassificator.getType(null));
    }
}
