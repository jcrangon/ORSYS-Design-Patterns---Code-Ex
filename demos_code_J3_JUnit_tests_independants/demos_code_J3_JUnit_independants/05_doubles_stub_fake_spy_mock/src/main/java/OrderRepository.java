public interface OrderRepository { void save(Order order); boolean exists(String id); }
