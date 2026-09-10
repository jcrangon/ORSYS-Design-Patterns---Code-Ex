public class OrderFactory {
    private final IdGenerator ids;
    private final ClockPort clock;
    public OrderFactory(IdGenerator ids, ClockPort clock) { this.ids = ids; this.clock = clock; }
    public Order create(int total) { return new Order(ids.next(), total, clock.now()); }
}
