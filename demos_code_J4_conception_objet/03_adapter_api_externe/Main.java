public class Main {
    record Money(long cents) { static Money euros(long e){ return new Money(e*100); } }
    record Receipt(String id) {}
    interface PaymentGateway { Receipt pay(Money amount); }

    // API externe que l'on ne contrôle pas
    static final class StripeClient {
        record StripeCharge(String chargeId, boolean ok) {}
        StripeCharge charge(long amountInCents){
            System.out.println("[Stripe SDK] charge " + amountInCents + " cents");
            return new StripeCharge("ch_123", true);
        }
    }

    static final class StripePaymentAdapter implements PaymentGateway {
        private final StripeClient client;
        StripePaymentAdapter(StripeClient client){ this.client=client; }
        public Receipt pay(Money amount){
            var c=client.charge(amount.cents());
            if(!c.ok()) throw new IllegalStateException("Paiement refusé");
            return new Receipt(c.chargeId());
        }
    }

    static final class Checkout {
        private final PaymentGateway gateway;
        Checkout(PaymentGateway gateway){ this.gateway=gateway; }
        Receipt checkout(Money total){ return gateway.pay(total); }
    }

    public static void main(String[] args){
        var checkout=new Checkout(new StripePaymentAdapter(new StripeClient()));
        System.out.println("Receipt domaine = " + checkout.checkout(Money.euros(49)));
        System.out.println("À commenter : Checkout ne dépend d'aucun type Stripe.");
    }
}
