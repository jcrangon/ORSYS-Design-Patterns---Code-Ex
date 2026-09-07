public class LegacyDemo {
    public static void main(String[] args) {
        LegacyShippingCalculator legacy = new LegacyShippingCalculator();
        CharacterizationSuite.capture("legacy", legacy::price);

        RefactoredShippingCalculator refactored = new RefactoredShippingCalculator();
        CharacterizationSuite.capture("refactored", refactored::price);

        for (int weight : new int[]{0,1,4,5,10}) {
            Checks.equals(legacy.price(weight), refactored.price(weight), "même comportement poids=" + weight);
        }
        System.out.println("Refactoring sûr : la structure change, les sorties caractérisées restent identiques.");
    }
    interface Price { int apply(int weight); }
    static final class LegacyShippingCalculator {
        int price(int weight) {
            if (weight <= 0) return 0;
            if (weight < 5) return 7;
            return 7 + (weight - 4) * 2;
        }
    }
    static final class RefactoredShippingCalculator {
        int price(int weight) {
            if (weight <= 0) return 0;
            int base = 7;
            int excessUnits = Math.max(0, weight - 4);
            return base + excessUnits * 2;
        }
    }
    static final class CharacterizationSuite {
        static void capture(String label, Price price) {
            System.out.println("--- " + label + " ---");
            for (int weight : new int[]{0,1,4,5,10})
                System.out.println(weight + "kg -> " + price.apply(weight));
        }
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
