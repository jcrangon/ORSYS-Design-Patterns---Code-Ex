public class DemoIspAvant {
    interface PaymentService {
        String find(String id);
        void capture(String id);
        void refund(String id);
        String exportCsv();
        void purgeAll();
    }

    static final class ReadOnlyPaymentView implements PaymentService {
        public String find(String id) { return "payment:" + id; }
        public void capture(String id) { throw new UnsupportedOperationException(); }
        public void refund(String id) { throw new UnsupportedOperationException(); }
        public String exportCsv() { throw new UnsupportedOperationException(); }
        public void purgeAll() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
        PaymentService view = new ReadOnlyPaymentView();
        System.out.println(view.find("P42"));
        try { view.refund("P42"); }
        catch (UnsupportedOperationException e) {
            System.out.println("Contrat mensonger : refund() n'est pas supporté");
        }
    }
}
