public class DemoIsp {
    interface PaymentReader { String find(String id); }
    interface PaymentCaptor { void capture(String id); }
    interface RefundManager { void refund(String id); }
    interface PaymentExporter { String exportCsv(); }

    static final class PaymentApplicationService
            implements PaymentReader, PaymentCaptor, RefundManager, PaymentExporter {
        public String find(String id) { return "payment:" + id; }
        public void capture(String id) { System.out.println("capture " + id); }
        public void refund(String id) { System.out.println("refund " + id); }
        public String exportCsv() { return "id,status\\nP42,CAPTURED"; }
    }

    static final class PaymentScreen {
        private final PaymentReader reader;
        PaymentScreen(PaymentReader reader) { this.reader = reader; }
        void render(String id) { System.out.println("UI -> " + reader.find(id)); }
    }

    static final class RefundUseCase {
        private final PaymentReader reader;
        private final RefundManager refunds;
        RefundUseCase(PaymentReader reader, RefundManager refunds) {
            this.reader = reader;
            this.refunds = refunds;
        }
        void execute(String id) {
            System.out.println("Avant remboursement : " + reader.find(id));
            refunds.refund(id);
        }
    }

    public static void main(String[] args) {
        PaymentApplicationService service = new PaymentApplicationService();
        new PaymentScreen(service).render("P42");
        new RefundUseCase(service, service).execute("P42");
    }
}
