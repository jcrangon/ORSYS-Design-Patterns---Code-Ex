import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountRulesTest {

    @ParameterizedTest(name = "{0} euros -> {1} euros")
    @CsvSource({
        "0, 0",
        "99, 99",
        "100, 90",
        "250, 225"
    })
    void appliesDiscountAtTheBoundary(int initial, int expected) {
        assertEquals(expected, DiscountRules.total(initial));
    }

    @Test
    void rejectsNegativeAmount() {
        IllegalArgumentException error = assertThrows(
            IllegalArgumentException.class,
            () -> DiscountRules.total(-1)
        );
        assertEquals("montant négatif", error.getMessage());
    }
}
