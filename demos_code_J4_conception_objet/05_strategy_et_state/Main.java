public class Main {
    interface DiscountStrategy { int apply(int total); }
    static final class StandardDiscount implements DiscountStrategy { public int apply(int t){ return t; } }
    static final class VipDiscount implements DiscountStrategy { public int apply(int t){ return (int)Math.round(t*0.80); } }
    static final class Checkout {
        private final DiscountStrategy discount;
        Checkout(DiscountStrategy d){ discount=d; }
        int total(int raw){ return discount.apply(raw); }
    }

    interface OrderState { OrderState next(); String name(); boolean canShip(); }
    static final class Draft implements OrderState {
        public OrderState next(){ return new Paid(); } public String name(){ return "DRAFT"; } public boolean canShip(){ return false; }
    }
    static final class Paid implements OrderState {
        public OrderState next(){ return new Shipped(); } public String name(){ return "PAID"; } public boolean canShip(){ return true; }
    }
    static final class Shipped implements OrderState {
        public OrderState next(){ return this; } public String name(){ return "SHIPPED"; } public boolean canShip(){ return false; }
    }
    static final class Order {
        private OrderState state=new Draft();
        void advance(){ state=state.next(); }
        void print(){ System.out.println("state="+state.name()+", canShip="+state.canShip()); }
    }

    public static void main(String[] args){
        System.out.println("=== Strategy : choix externe ===");
        System.out.println("standard 100 -> " + new Checkout(new StandardDiscount()).total(100));
        System.out.println("VIP      100 -> " + new Checkout(new VipDiscount()).total(100));
        System.out.println("\n=== State : transition interne ===");
        var order=new Order(); order.print(); order.advance(); order.print(); order.advance(); order.print();
    }
}
