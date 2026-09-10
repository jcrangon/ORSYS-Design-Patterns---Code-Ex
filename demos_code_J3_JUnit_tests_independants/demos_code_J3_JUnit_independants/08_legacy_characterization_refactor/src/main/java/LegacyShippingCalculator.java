public class LegacyShippingCalculator {
    public int price(int weight) {
        if (weight <= 0) return 0;
        if (weight < 5) return 7;
        return 7 + (weight - 4) * 2;
    }
}
