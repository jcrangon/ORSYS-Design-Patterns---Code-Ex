import java.util.*;

public class Main {
    record ReservationId(String value) {}
    record Reservation(ReservationId id, String room, String user) {}

    interface ReservationRepository {
        Optional<Reservation> findById(ReservationId id);
        void save(Reservation reservation);
    }

    static final class InMemoryReservationRepository implements ReservationRepository {
        private final Map<ReservationId,Reservation> data = new HashMap<>();
        public Optional<Reservation> findById(ReservationId id){ return Optional.ofNullable(data.get(id)); }
        public void save(Reservation r){ data.put(r.id(), r); }
    }

    static final class ReservationService {
        private final ReservationRepository repo;
        ReservationService(ReservationRepository repo){ this.repo=repo; }
        Reservation create(String id,String room,String user){
            var rid=new ReservationId(id);
            if(repo.findById(rid).isPresent()) throw new IllegalStateException("Déjà réservée");
            var r=new Reservation(rid,room,user); repo.save(r); return r;
        }
    }

    public static void main(String[] args){
        ReservationRepository repo=new InMemoryReservationRepository();
        var service=new ReservationService(repo);
        System.out.println(service.create("R-42","A101","Alice"));
        System.out.println("Persisté ? " + repo.findById(new ReservationId("R-42")).isPresent());
        System.out.println("À commenter : le service ne connaît ni SQL, ni MongoDB, ni fichier.");
    }
}
