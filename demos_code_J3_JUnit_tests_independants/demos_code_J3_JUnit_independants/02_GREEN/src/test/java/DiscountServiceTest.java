import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {

    @Test
    void premiumCustomerGetsFifteenPercentDiscount() {
        DiscountService service = new DiscountService();
        assertEquals(170, service.totalFor(true, 200));
    }

    @Test
    void standardCustomerPaysFullPrice() {
        DiscountService service = new DiscountService();
        assertEquals(200, service.totalFor(false, 200));
    }
}
