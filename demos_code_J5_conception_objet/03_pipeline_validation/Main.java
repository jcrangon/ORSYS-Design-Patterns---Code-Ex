import java.util.*;
import java.util.function.UnaryOperator;

public class Main {
    record Booking(String user,String room,boolean paid,List<String> tags) {
        Booking withTag(String t){ var copy=new ArrayList<>(tags); copy.add(t); return new Booking(user,room,paid,List.copyOf(copy)); }
    }

    static UnaryOperator<Booking> validate(){
        return b -> { if(b.user()==null || b.user().isBlank()) throw new IllegalArgumentException("user requis"); return b.withTag("validated"); };
    }
    static UnaryOperator<Booking> enrich(){ return b -> b.withTag("room:"+b.room()); }
    static UnaryOperator<Booking> checkPayment(){
        return b -> { if(!b.paid()) throw new IllegalStateException("paiement requis"); return b.withTag("payment-ok"); };
    }

    static Booking run(Booking input,List<UnaryOperator<Booking>> steps){
        Booking current=input;
        for(var step:steps) current=step.apply(current);
        return current;
    }

    public static void main(String[] args){
        var pipeline=List.of(validate(),enrich(),checkPayment());
        var ok=new Booking("Alice","A101",true,List.of());
        System.out.println(run(ok,pipeline));
        try { run(new Booking("Bob","A102",false,List.of()),pipeline); }
        catch(Exception e){ System.out.println("Refus attendu : " + e.getMessage()); }
        System.out.println("À commenter : chaque étape est petite, explicite, testable et recomposable.");
    }
}
