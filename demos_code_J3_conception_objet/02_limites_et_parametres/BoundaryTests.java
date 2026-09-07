public class BoundaryTests {
    public static void main(String[] args) {
        int[][] cases = { {0,0}, {99,99}, {100,90}, {250,225} };
        for (int[] c : cases) {
            int actual = DiscountRules.total(c[0]);
            Checks.equals(c[1], actual, "cas paramétré pour " + c[0]);
            System.out.println("OK " + c[0] + " -> " + actual);
        }
        Checks.fails(() -> DiscountRules.total(-1), IllegalArgumentException.class,
                "un montant négatif doit être refusé");
        System.out.println("OK -1 -> erreur métier contrôlée");
    }
}
final class DiscountRules {
    static int total(int initial) {
        if (initial < 0) throw new IllegalArgumentException("montant négatif");
        return initial >= 100 ? initial * 90 / 100 : initial;
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
