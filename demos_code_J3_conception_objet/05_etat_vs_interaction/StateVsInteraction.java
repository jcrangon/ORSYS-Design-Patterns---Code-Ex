import java.util.*;

public class StateVsInteraction {
    public static void main(String[] args) {
        MailerSpy mailer = new MailerSpy();
        Checkout checkout = new Checkout(mailer);
        Receipt receipt = checkout.confirm(new Order("alice@example.test", 100));

        // Test d'état : le contrat principal
        Checks.equals("APPROVED", receipt.status(), "statut métier");

        // Test d'interaction : effet externe significatif
        Checks.equals(List.of("alice@example.test"), mailer.sentTo, "mail de confirmation");

        System.out.println("État        -> " + receipt);
        System.out.println("Interaction -> sendConfirmation vers " + mailer.sentTo);
        System.out.println("On ne vérifie PAS l'ordre des appels privés ni la structure interne.");
    }
    record Order(String email, int total) {}
    record Receipt(String status) {}
    interface Mailer { void sendConfirmation(String email); }
    static final class MailerSpy implements Mailer {
        final List<String> sentTo = new ArrayList<>();
        public void sendConfirmation(String email) { sentTo.add(email); }
    }
    static final class Checkout {
        private final Mailer mailer; Checkout(Mailer mailer) { this.mailer=mailer; }
        Receipt confirm(Order order) {
            Receipt r = new Receipt("APPROVED");
            mailer.sendConfirmation(order.email());
            return r;
        }
    }
}

final class Checks {
    private Checks() {}
    static void equals(Object expected, Object actual, String message) {
        if (!java.util.Objects.equals(expected, actual)) {
            throw new AssertionError(message + " | attendu=" + expected + ", obtenu=" + actual);
        }
    }
    static void isTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static void fails(Runnable action, Class<? extends Throwable> type, String message) {
        try { action.run(); }
        catch (Throwable t) {
            if (type.isInstance(t)) return;
            throw new AssertionError(message + " | exception=" + t, t);
        }
        throw new AssertionError(message + " | aucune exception");
    }
}
