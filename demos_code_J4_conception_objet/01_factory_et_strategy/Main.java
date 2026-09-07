import java.util.*;

public class Main {
    interface Sender { void send(String message); }
    static final class EmailSender implements Sender { public void send(String m){ System.out.println("EMAIL -> " + m); } }
    static final class SmsSender implements Sender { public void send(String m){ System.out.println("SMS   -> " + m); } }
    static final class PushSender implements Sender { public void send(String m){ System.out.println("PUSH  -> " + m); } }

    static final class SenderFactory {
        static Sender create(String type) {
            return switch(type.toLowerCase()) {
                case "email" -> new EmailSender();
                case "sms" -> new SmsSender();
                case "push" -> new PushSender();
                default -> throw new IllegalArgumentException("Canal inconnu: " + type);
            };
        }
    }

    static final class NotificationService {
        private final Sender sender;
        NotificationService(Sender sender) { this.sender = sender; }
        void notify(String message) { sender.send(message); }
    }

    public static void main(String[] args) {
        System.out.println("=== Factory choisit, Strategy exécute ===");
        for (String type : List.of("email", "sms", "push")) {
            Sender sender = SenderFactory.create(type);
            new NotificationService(sender).notify("Commande #42 confirmée");
        }
        System.out.println("\nÀ commenter : NotificationService ne connaît aucune classe concrète.");
    }
}
