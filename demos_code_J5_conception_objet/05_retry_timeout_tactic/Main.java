public class Main {
    interface PaymentApi { String charge(String requestId,int amount); }
    static final class FlakyPaymentApi implements PaymentApi {
        private int calls=0;
        public String charge(String requestId,int amount){
            calls++;
            System.out.println("appel fournisseur #"+calls+" requestId="+requestId);
            if(calls<3) throw new RuntimeException("timeout simulé");
            return "PAY-OK-"+amount;
        }
    }
    static final class RetryingPaymentGateway {
        private final PaymentApi api; private final int maxAttempts;
        RetryingPaymentGateway(PaymentApi api,int maxAttempts){ this.api=api; this.maxAttempts=maxAttempts; }
        String charge(String requestId,int amount){
            RuntimeException last=null;
            for(int attempt=1;attempt<=maxAttempts;attempt++){
                try { return api.charge(requestId,amount); }
                catch(RuntimeException e){ last=e; System.out.println("retry après " + e.getMessage()); }
            }
            throw last;
        }
    }
    public static void main(String[] args){
        var gateway=new RetryingPaymentGateway(new FlakyPaymentApi(),3);
        System.out.println("Résultat = " + gateway.charge("req-42",120));
        System.out.println("À commenter : retry améliore la disponibilité mais exige des opérations idempotentes.");
    }
}
