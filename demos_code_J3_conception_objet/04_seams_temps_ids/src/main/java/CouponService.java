import java.time.Instant;
public class CouponService {
    private final ClockPort clock;
    public CouponService(ClockPort clock) { this.clock = clock; }
    public boolean validUntil(Instant expiration) { return clock.now().isBefore(expiration); }
}
