import java.util.HashMap;
import java.util.Map;

public class DemoInjection {
    interface Clock { long now(); }

    static final class SystemClock implements Clock {
        public long now() { return System.currentTimeMillis(); }
    }

    static final class ServiceLocator {
        private static final Map<Class<?>, Object> services = new HashMap<>();
        static <T> void register(Class<T> type, T instance) { services.put(type, instance); }
        static <T> T get(Class<T> type) { return type.cast(services.get(type)); }
    }

    static final class TokenServiceWithLocator {
        String createToken(String user) {
            // Dépendance invisible dans le constructeur et l'API de la classe.
            long timestamp = ServiceLocator.get(Clock.class).now();
            return user + "-" + timestamp;
        }
    }

    static final class TokenServiceWithInjection {
        private final Clock clock;
        TokenServiceWithInjection(Clock clock) { this.clock = clock; }
        String createToken(String user) { return user + "-" + clock.now(); }
    }

    public static void main(String[] args) {
        System.out.println("=== Service Locator ===");
        ServiceLocator.register(Clock.class, new SystemClock());
        System.out.println(new TokenServiceWithLocator().createToken("alice"));

        System.out.println("\n=== Injection constructeur ===");
        Clock fixedClock = () -> 1_700_000_000_000L;
        TokenServiceWithInjection service = new TokenServiceWithInjection(fixedClock);
        System.out.println(service.createToken("alice"));
        System.out.println("Même entrée, même sortie : " + service.createToken("alice"));
    }
}
