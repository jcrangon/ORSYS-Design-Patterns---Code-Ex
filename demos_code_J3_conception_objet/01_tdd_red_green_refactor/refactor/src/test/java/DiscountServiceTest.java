import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {

    @Test
    void premiumBehaviorIsStillTheSameAfterRefactor() {
        DiscountService service = new DiscountService(new PremiumPolicy());
        assertEquals(170, service.totalFor(200));
    }

    @Test
    void standardBehaviorIsStillTheSameAfterRefactor() {
        DiscountService service = new DiscountService(new NoDiscountPolicy());
        assertEquals(200, service.totalFor(200));
    }
}
