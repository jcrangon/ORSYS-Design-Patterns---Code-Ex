public class Main {
    record Request(String user, int amount) {}
    interface Handler { void handle(Request r, Runnable next); }
    static final class AuthHandler implements Handler {
        public void handle(Request r,Runnable next){ if(r.user()==null) throw new IllegalStateException("non authentifié"); System.out.println("auth OK"); next.run(); }
    }
    static final class QuotaHandler implements Handler {
        public void handle(Request r,Runnable next){ if(r.amount()>1000) throw new IllegalStateException("quota dépassé"); System.out.println("quota OK"); next.run(); }
    }
    interface Command { void execute(); }
    static final class CapturePayment implements Command {
        private final Request r; CapturePayment(Request r){ this.r=r; }
        public void execute(){ System.out.println("CAPTURE " + r.amount() + "€ pour " + r.user()); }
    }
    static void runChain(Request r, Handler a, Handler b, Command cmd){
        a.handle(r, () -> b.handle(r, cmd::execute));
    }
    public static void main(String[] args){
        System.out.println("=== Requête valide ===");
        runChain(new Request("alice",250),new AuthHandler(),new QuotaHandler(),new CapturePayment(new Request("alice",250)));
        System.out.println("\n=== Requête refusée ===");
        try { runChain(new Request("bob",1500),new AuthHandler(),new QuotaHandler(),new CapturePayment(new Request("bob",1500))); }
        catch(Exception e){ System.out.println("STOP : " + e.getMessage()); }
    }
}
