public class RedPhase {
    public static void main(String[] args) {
        DiscountService service = new DiscountService();
        try {
            Checks.equals(170, service.totalFor(true, 200), "premium: -15 %");
            System.out.println("ERREUR PEDAGOGIQUE: le test aurait dû être rouge.");
        } catch (AssertionError e) {
            System.out.println("RED attendu -> " + e.getMessage());
            System.out.println("Le test échoue pour la bonne raison: le comportement manque.");
        }
    }
    static final class DiscountService {
        int totalFor(boolean premium, int total) { return total; }
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
