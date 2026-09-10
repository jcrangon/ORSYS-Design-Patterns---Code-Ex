public class RefactoredShippingCalculator {
    public int price(int weight) {
        if (weight <= 0) return 0;
        int base = 7;
        int excessUnits = Math.max(0, weight - 4);
        return base + excessUnits * 2;
    }
}
