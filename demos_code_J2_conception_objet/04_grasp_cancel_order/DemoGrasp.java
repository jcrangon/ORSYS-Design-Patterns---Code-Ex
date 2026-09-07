import java.util.HashMap;
import java.util.Map;

public class DemoGrasp {
    enum OrderStatus { CREATED, PAID, SHIPPED, CANCELLED }

    static final class Order {
        private final String id;
        private OrderStatus status;
        private final int paidAmountCents;

        Order(String id, OrderStatus status, int paidAmountCents) {
            this.id = id;
            this.status = status;
            this.paidAmountCents = paidAmountCents;
        }

        String id() { return id; }
        int paidAmountCents() { return paidAmountCents; }
        OrderStatus status() { return status; }

        // Information Expert : Order possède l'état nécessaire pour décider.
        boolean canBeCancelled() { return status != OrderStatus.SHIPPED && status != OrderStatus.CANCELLED; }

        void cancel() {
            if (!canBeCancelled()) throw new IllegalStateException("Commande non annulable : " + status);
            status = OrderStatus.CANCELLED;
        }
    }

    interface OrderRepository {
        Order get(String id);
        void save(Order order);
    }

    interface RefundPort { void refund(String orderId, int cents); }
    interface NotificationPort { void cancelled(String orderId); }

    // Controller GRASP : coordonne le cas d'usage, sans absorber la règle métier.
    static final class CancelOrderUseCase {
        private final OrderRepository orders;
        private final RefundPort refunds;
        private final NotificationPort notifications;

        CancelOrderUseCase(OrderRepository orders, RefundPort refunds, NotificationPort notifications) {
            this.orders = orders;
            this.refunds = refunds;
            this.notifications = notifications;
        }

        void execute(String orderId) {
            Order order = orders.get(orderId);
            order.cancel();
            if (order.paidAmountCents() > 0) refunds.refund(order.id(), order.paidAmountCents());
            orders.save(order);
            notifications.cancelled(order.id());
        }
    }

    // Pure Fabrication : dépôt mémoire pour la démo, pas une responsabilité d'Order.
    static final class InMemoryOrderRepository implements OrderRepository {
        private final Map<String, Order> data = new HashMap<>();
        void add(Order order) { data.put(order.id(), order); }
        public Order get(String id) { return data.get(id); }
        public void save(Order order) { data.put(order.id(), order); }
    }

    public static void main(String[] args) {
        InMemoryOrderRepository repo = new InMemoryOrderRepository();
        repo.add(new Order("O-42", OrderStatus.PAID, 12900));

        RefundPort refund = (id, cents) -> System.out.println("Refund " + cents + " cents pour " + id);
        NotificationPort notification = id -> System.out.println("Email : commande " + id + " annulée");

        CancelOrderUseCase useCase = new CancelOrderUseCase(repo, refund, notification);
        useCase.execute("O-42");
        System.out.println("Statut final : " + repo.get("O-42").status());
    }
}
