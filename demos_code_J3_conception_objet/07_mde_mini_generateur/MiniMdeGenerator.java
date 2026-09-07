import java.nio.file.*;
import java.util.*;

public class MiniMdeGenerator {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("model.txt"));
        if (lines.isEmpty() || !lines.get(0).startsWith("entity "))
            throw new IllegalArgumentException("modèle invalide");
        String name = lines.get(0).substring("entity ".length()).trim();
        List<String> fields = new ArrayList<>();
        for (int i=1;i<lines.size();i++) {
            String[] p=lines.get(i).trim().split("\s+");
            if (p.length==3 && p[0].equals("field")) fields.add(p[2]+" "+p[1]);
        }
        String code = "public record " + name + "(" + String.join(", ", fields) + ") {}\n";
        Files.createDirectories(Path.of("generated"));
        Files.writeString(Path.of("generated", name+".java"), code);
        System.out.println("Généré -> generated/" + name + ".java");
        System.out.println(code);
    }
}
