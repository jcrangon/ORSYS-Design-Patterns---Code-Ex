public class PremiumPolicy implements DiscountPolicy {
    @Override
    public int apply(int total) {
        return total * 85 / 100;
    }
}
