public final class DiscountRules {
    private DiscountRules() {}

    public static int total(int initial) {
        if (initial < 0) {
            throw new IllegalArgumentException("montant négatif");
        }
        return initial >= 100 ? initial * 90 / 100 : initial;
    }
}
