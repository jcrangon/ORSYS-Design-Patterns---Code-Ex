import java.time.*;
import java.util.*;

public class SeamsDemo {
    public static void main(String[] args) {
        ClockPort clock = () -> Instant.parse("2026-09-06T10:00:00Z");
        IdGenerator ids = () -> UUID.fromString("00000000-0000-0000-0000-000000000001");

        CouponService coupons = new CouponService(clock);
        OrderFactory factory = new OrderFactory(ids, clock);

        Checks.isTrue(coupons.validUntil(Instant.parse("2026-09-07T00:00:00Z")), "coupon valide");
        Order order = factory.create(120);
        Checks.equals("00000000-0000-0000-0000-000000000001", order.id().toString(), "id déterministe");
        Checks.equals(Instant.parse("2026-09-06T10:00:00Z"), order.createdAt(), "temps déterministe");

        System.out.println(order);
        System.out.println("Même entrée -> même résultat, sans dépendre de l'heure réelle ni d'un UUID aléatoire.");
    }
    interface ClockPort { Instant now(); }
    interface IdGenerator { UUID next(); }
    record Order(UUID id, int total, Instant createdAt) {}
    static final class CouponService {
        private final ClockPort clock; CouponService(ClockPort clock) { this.clock=clock; }
        boolean validUntil(Instant expiration) { return clock.now().isBefore(expiration); }
    }
    static final class OrderFactory {
        private final IdGenerator ids; private final ClockPort clock;
        OrderFactory(IdGenerator ids, ClockPort clock) { this.ids=ids; this.clock=clock; }
        Order create(int total) { return new Order(ids.next(), total, clock.now()); }
    }
}

final class Checks {
    private Checks() {}
    static void equals(Object expected, Object actual, String message) {
        if (!java.util.Objects.equals(expected, actual)) {
            throw new AssertionError(message + " | attendu=" + expected + ", obtenu=" + actual);
        }
    }
    static void isTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static void fails(Runnable action, Class<? extends Throwable> type, String message) {
        try { action.run(); }
        catch (Throwable t) {
            if (type.isInstance(t)) return;
            throw new AssertionError(message + " | exception=" + t, t);
        }
        throw new AssertionError(message + " | aucune exception");
    }
}
