import java.util.*;
import java.util.function.Consumer;

public class Main {
    record OrderConfirmed(String orderId) {}
    static final class EventBus {
        private final List<Consumer<OrderConfirmed>> listeners=new ArrayList<>();
        void subscribe(Consumer<OrderConfirmed> l){ listeners.add(l); }
        void publish(OrderConfirmed e){
            for(var l:listeners) {
                try { l.accept(e); }
                catch(RuntimeException ex){ System.out.println("[ERREUR LISTENER] "+ex.getMessage()); }
            }
        }
    }
    static final class OrderService {
        private final EventBus events;
        OrderService(EventBus e){ events=e; }
        void confirm(String id){
            System.out.println("Commande " + id + " confirmée");
            events.publish(new OrderConfirmed(id));
        }
    }
    public static void main(String[] args){
        var bus=new EventBus();
        bus.subscribe(e -> System.out.println("[EMAIL] " + e.orderId()));
        bus.subscribe(e -> System.out.println("[ANALYTICS] " + e.orderId()));
        bus.subscribe(e -> { throw new RuntimeException("facturation indisponible"); });
        new OrderService(bus).confirm("42");
        System.out.println("À commenter : OrderService ignore combien de réactions existent.");
    }
}
