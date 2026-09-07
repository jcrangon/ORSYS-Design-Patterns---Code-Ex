import java.time.*;
import java.util.*;

public class Main {
    static final class Metrics {
        private final Map<String,Integer> counters=new HashMap<>();
        void inc(String key){ counters.merge(key,1,Integer::sum); }
        void dump(){ counters.forEach((k,v)->System.out.println("METRIC "+k+"="+v)); }
    }
    static void log(String level,String traceId,String event,String details){
        System.out.printf("%s level=%s traceId=%s event=%s %s%n",Instant.now(),level,traceId,event,details);
    }
    public static void main(String[] args){
        var metrics=new Metrics(); String traceId="trace-7f3a";
        log("INFO",traceId,"reservation.requested","room=A101 user=Alice");
        metrics.inc("reservation.requested");
        log("INFO",traceId,"payment.accepted","amount=120");
        log("INFO",traceId,"reservation.confirmed","reservationId=R-42");
        metrics.inc("reservation.confirmed");
        metrics.dump();
        System.out.println("À commenter : sans traceId, reconstituer le parcours distribué devient coûteux.");
    }
}
