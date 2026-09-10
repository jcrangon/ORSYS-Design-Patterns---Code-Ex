public class DiscountService {
    public int totalFor(boolean premium, int total) {
        if (premium) {
            return total * 85 / 100;
        }
        return total;
    }
}
