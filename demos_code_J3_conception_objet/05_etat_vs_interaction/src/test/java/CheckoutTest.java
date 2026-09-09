import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {

    @Test
    void stateTestChecksTheBusinessResult() {
        MailerSpy mailer = new MailerSpy();
        Checkout checkout = new Checkout(mailer);

        Receipt receipt = checkout.confirm(new Order("alice@example.test", 100));

        assertEquals("APPROVED", receipt.status());
    }

    @Test
    void interactionTestChecksASignificantExternalEffect() {
        MailerSpy mailer = new MailerSpy();
        Checkout checkout = new Checkout(mailer);

        checkout.confirm(new Order("alice@example.test", 100));

        assertEquals(List.of("alice@example.test"), mailer.sentTo);
    }

    static final class MailerSpy implements Mailer {
        final List<String> sentTo = new ArrayList<>();
        public void sendConfirmation(String email) { sentTo.add(email); }
    }
}
