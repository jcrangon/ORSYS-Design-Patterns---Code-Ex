public class RefactorPhase {
    public static void main(String[] args) {
        DiscountService premium = new DiscountService(new PremiumPolicy());
        DiscountService standard = new DiscountService(new NoDiscountPolicy());
        Checks.equals(170, premium.totalFor(200), "premium");
        Checks.equals(200, standard.totalFor(200), "standard");
        System.out.println("REFACTOR -> mêmes comportements, responsabilités plus explicites.");
    }
    interface DiscountPolicy { int apply(int total); }
    static final class PremiumPolicy implements DiscountPolicy {
        public int apply(int total) { return total * 85 / 100; }
    }
    static final class NoDiscountPolicy implements DiscountPolicy {
        public int apply(int total) { return total; }
    }
    static final class DiscountService {
        private final DiscountPolicy policy;
        DiscountService(DiscountPolicy policy) { this.policy = policy; }
        int totalFor(int total) { return policy.apply(total); }
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
