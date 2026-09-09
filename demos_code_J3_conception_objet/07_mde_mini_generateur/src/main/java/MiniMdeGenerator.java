import java.nio.file.*;
import java.util.*;

public final class MiniMdeGenerator {
    private MiniMdeGenerator() {}

    public static Path generate(Path model, Path outputDir) throws Exception {
        List<String> lines = Files.readAllLines(model);
        if (lines.isEmpty() || !lines.get(0).startsWith("entity ")) {
            throw new IllegalArgumentException("modèle invalide");
        }

        String name = lines.get(0).substring("entity ".length()).trim();
        List<String> fields = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] p = lines.get(i).trim().split("\s+");
            if (p.length == 3 && p[0].equals("field")) {
                fields.add(p[2] + " " + p[1]);
            }
        }

        String code = "public record " + name + "(" + String.join(", ", fields) + ") {}\n";
        Files.createDirectories(outputDir);
        Path generated = outputDir.resolve(name + ".java");
        Files.writeString(generated, code);
        return generated;
    }
}
