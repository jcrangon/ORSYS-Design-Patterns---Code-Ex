import java.util.*;
import java.util.function.Consumer;

public class Main {
    record Cart(int total) {}
    record Receipt(String id, int paid) {}
    interface DiscountStrategy { int apply(Cart c); }
    static final class NoDiscount implements DiscountStrategy { public int apply(Cart c){ return c.total(); } }
    static final class VipDiscount implements DiscountStrategy { public int apply(Cart c){ return (int)Math.round(c.total()*0.8); } }

    interface PaymentGateway { Receipt pay(int amount); }
    static final class StripeGateway implements PaymentGateway { public Receipt pay(int a){ return new Receipt("stripe-001",a); } }
    static final class PaypalGateway implements PaymentGateway { public Receipt pay(int a){ return new Receipt("paypal-001",a); } }
    static final class PaymentFactory {
        static PaymentGateway create(String provider){
            return switch(provider){ case "stripe" -> new StripeGateway(); case "paypal" -> new PaypalGateway(); default -> throw new IllegalArgumentException(provider); };
        }
    }
    static final class AuditGatewayDecorator implements PaymentGateway {
        private final PaymentGateway target;
        AuditGatewayDecorator(PaymentGateway t){ target=t; }
        public Receipt pay(int amount){ System.out.println("[AUDIT] paiement demandé="+amount); var r=target.pay(amount); System.out.println("[AUDIT] reçu="+r.id()); return r; }
    }

    record OrderPaid(Receipt receipt) {}
    static final class EventBus {
        private final List<Consumer<OrderPaid>> listeners=new ArrayList<>();
        void subscribe(Consumer<OrderPaid> l){ listeners.add(l); }
        void publish(OrderPaid e){ listeners.forEach(l -> l.accept(e)); }
    }
    static final class CheckoutService {
        private final DiscountStrategy discount; private final PaymentGateway gateway; private final EventBus events;
        CheckoutService(DiscountStrategy d, PaymentGateway g, EventBus e){ discount=d; gateway=g; events=e; }
        Receipt checkout(Cart cart){ int amount=discount.apply(cart); var r=gateway.pay(amount); events.publish(new OrderPaid(r)); return r; }
    }

    static void assertEq(Object expected,Object actual,String label){ if(!Objects.equals(expected,actual)) throw new AssertionError(label+" expected="+expected+" actual="+actual); System.out.println("[PASS] "+label); }

    public static void main(String[] args){
        var bus=new EventBus();
        var notifications=new ArrayList<String>();
        bus.subscribe(e -> { notifications.add("email:"+e.receipt().id()); System.out.println("[EMAIL] paiement confirmé"); });
        PaymentGateway gateway=new AuditGatewayDecorator(PaymentFactory.create("stripe"));
        var checkout=new CheckoutService(new VipDiscount(),gateway,bus);
        Receipt r=checkout.checkout(new Cart(100));
        assertEq(80,r.paid(),"VIP applique 20% de remise");
        assertEq(1,notifications.size(),"un événement déclenche une notification");
        System.out.println("\nExtension à demander au groupe : ajouter ApplePay sans modifier CheckoutService.");
    }
}
