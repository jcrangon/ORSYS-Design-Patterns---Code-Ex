public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public int apply(int total) {
        return total;
    }
}
