import java.util.*;
import java.util.function.Consumer;

public class Main {
    record ReservationConfirmed(String eventId,String reservationId) {}

    static final class EventBus {
        private final List<Consumer<ReservationConfirmed>> listeners=new ArrayList<>();
        void subscribe(Consumer<ReservationConfirmed> l){ listeners.add(l); }
        void publish(ReservationConfirmed e){
            System.out.println("[BUS] publish " + e.eventId());
            listeners.forEach(l -> l.accept(e));
        }
    }

    static final class EmailConsumer implements Consumer<ReservationConfirmed> {
        private final Set<String> processed=new HashSet<>();
        public void accept(ReservationConfirmed e){
            if(!processed.add(e.eventId())) { System.out.println("[EMAIL] doublon ignoré " + e.eventId()); return; }
            System.out.println("[EMAIL] confirmation " + e.reservationId());
        }
    }

    public static void main(String[] args){
        var bus=new EventBus();
        bus.subscribe(new EmailConsumer());
        bus.subscribe(e -> System.out.println("[ANALYTICS] reservation=" + e.reservationId()));
        var evt=new ReservationConfirmed("evt-001","R-42");
        bus.publish(evt);
        System.out.println("-- redelivery simulée --");
        bus.publish(evt);
        System.out.println("À commenter : le bus découple, mais la fiabilité crée de nouvelles responsabilités.");
    }
}
