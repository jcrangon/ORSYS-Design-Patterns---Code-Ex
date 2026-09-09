public class Checkout {
    private final Mailer mailer;
    public Checkout(Mailer mailer) { this.mailer = mailer; }

    public Receipt confirm(Order order) {
        Receipt receipt = new Receipt("APPROVED");
        mailer.sendConfirmation(order.email());
        return receipt;
    }
}
