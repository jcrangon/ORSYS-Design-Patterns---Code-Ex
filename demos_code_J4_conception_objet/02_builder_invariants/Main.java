import java.util.*;

public class Main {
    record Item(String name, int qty) {
        Item { if (qty <= 0) throw new IllegalArgumentException("qty > 0 obligatoire"); }
    }
    static final class Order {
        private final String customer;
        private final List<Item> items;
        private final String coupon;
        private Order(Builder b) {
            this.customer=b.customer; this.items=List.copyOf(b.items); this.coupon=b.coupon;
        }
        static Builder builder(){ return new Builder(); }
        static final class Builder {
            String customer; List<Item> items=new ArrayList<>(); String coupon;
            Builder customer(String c){ customer=c; return this; }
            Builder addItem(String n,int q){ items.add(new Item(n,q)); return this; }
            Builder coupon(String c){ coupon=c; return this; }
            Order build(){
                if(customer==null || customer.isBlank()) throw new IllegalStateException("customer obligatoire");
                if(items.isEmpty()) throw new IllegalStateException("au moins un article");
                return new Order(this);
            }
        }
        public String toString(){ return "Order{customer='"+customer+"', items="+items+", coupon="+coupon+"}"; }
    }
    public static void main(String[] args){
        System.out.println("=== Construction valide ===");
        var order=Order.builder().customer("Alice").addItem("Livre",2).coupon("WELCOME").build();
        System.out.println(order);
        System.out.println("\n=== Construction invalide ===");
        try { Order.builder().customer("Alice").build(); }
        catch (IllegalStateException e){ System.out.println("Refus attendu : "+e.getMessage()); }
    }
}
