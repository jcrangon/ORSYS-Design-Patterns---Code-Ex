public class StandardDiscounts implements DiscountPolicy {
    public int apply(Customer customer, int total) {
        return customer == Customer.PREMIUM ? total * 85 / 100 : total;
    }
}
