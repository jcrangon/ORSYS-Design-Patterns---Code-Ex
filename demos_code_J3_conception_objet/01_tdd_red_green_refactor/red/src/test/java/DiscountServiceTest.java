import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {

    @Test
    void premiumCustomerGetsFifteenPercentDiscount() {
        DiscountService service = new DiscountService();

        int resultat = service.totalFor(true, 200);

        assertEquals(170, resultat);
    }
}
