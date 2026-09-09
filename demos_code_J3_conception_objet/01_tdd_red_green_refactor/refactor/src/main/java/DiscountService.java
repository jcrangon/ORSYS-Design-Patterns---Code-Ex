public class DiscountService {
    private final DiscountPolicy policy;

    public DiscountService(DiscountPolicy policy) {
        this.policy = policy;
    }

    public int totalFor(int total) {
        return policy.apply(total);
    }
}
