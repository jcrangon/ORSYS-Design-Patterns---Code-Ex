import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingCharacterizationTest {

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 7",
        "4, 7",
        "5, 9",
        "10, 19"
    })
    void characterizesCurrentLegacyBehavior(int weight, int expected) {
        LegacyShippingCalculator legacy = new LegacyShippingCalculator();
        assertEquals(expected, legacy.price(weight));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 7",
        "4, 7",
        "5, 9",
        "10, 19"
    })
    void refactoredVersionPreservesCharacterizedBehavior(int weight, int expected) {
        RefactoredShippingCalculator refactored = new RefactoredShippingCalculator();
        assertEquals(expected, refactored.price(weight));
    }
}
