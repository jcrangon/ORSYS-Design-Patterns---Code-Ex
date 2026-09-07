import java.util.*;
import java.util.function.Consumer;

public class Main {
    record Reservation(String id,String room,String user,int amount) {}
    interface ReservationRepository { void save(Reservation r); }
    interface PaymentGateway { String charge(String reservationId,int amount); }
    record ReservationConfirmed(String id,String paymentId) {}

    static final class InMemoryReservationRepository implements ReservationRepository {
        final List<Reservation> saved=new ArrayList<>();
        public void save(Reservation r){ saved.add(r); System.out.println("[REPO] saved "+r.id()); }
    }
    static final class FakePaymentGateway implements PaymentGateway {
        public String charge(String id,int amount){ System.out.println("[PAYMENT] "+amount+"€ for "+id); return "pay-001"; }
    }
    static final class EventBus {
        final List<Consumer<ReservationConfirmed>> listeners=new ArrayList<>();
        void subscribe(Consumer<ReservationConfirmed> c){ listeners.add(c); }
        void publish(ReservationConfirmed e){ listeners.forEach(c->c.accept(e)); }
    }
    static final class ConfirmReservation {
        private final ReservationRepository repo; private final PaymentGateway payment; private final EventBus bus;
        ConfirmReservation(ReservationRepository r,PaymentGateway p,EventBus b){ repo=r; payment=p; bus=b; }
        void execute(Reservation r){
            String paymentId=payment.charge(r.id(),r.amount());
            repo.save(r);
            bus.publish(new ReservationConfirmed(r.id(),paymentId));
        }
    }
    public static void main(String[] args){
        var repo=new InMemoryReservationRepository(); var bus=new EventBus();
        bus.subscribe(e->System.out.println("[EMAIL] confirmation "+e.id()));
        bus.subscribe(e->System.out.println("[ANALYTICS] reservation "+e.id()));
        var useCase=new ConfirmReservation(repo,new FakePaymentGateway(),bus);
        useCase.execute(new Reservation("R-42","A101","Alice",120));
        System.out.println("Assertions : saved="+(repo.saved.size()==1));
        System.out.println("À commenter : le cœur orchestre des ports ; l'infrastructure reste remplaçable et testable.");
    }
}
