import java.nio.file.*;
public class GeneratedArtifactTest {
    public static void main(String[] args) throws Exception {
        String code = Files.readString(Path.of("generated/Order.java"));
        Checks.isTrue(code.contains("record Order"), "type généré");
        Checks.isTrue(code.contains("String id"), "champ id");
        Checks.isTrue(code.contains("int total"), "champ total");
        System.out.println("Validation artefact -> OK");
    }
}

final class Checks {
    private Checks() {}
    static void equals(Object expected, Object actual, String message) {
        if (!java.util.Objects.equals(expected, actual)) {
            throw new AssertionError(message + " | attendu=" + expected + ", obtenu=" + actual);
        }
    }
    static void isTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static void fails(Runnable action, Class<? extends Throwable> type, String message) {
        try { action.run(); }
        catch (Throwable t) {
            if (type.isInstance(t)) return;
            throw new AssertionError(message + " | exception=" + t, t);
        }
        throw new AssertionError(message + " | aucune exception");
    }
}
