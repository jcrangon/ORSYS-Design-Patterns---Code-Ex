import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class MiniMdeGeneratorTest {

    @TempDir
    Path temp;

    @Test
    void generatesAJavaRecordFromTheModel() throws Exception {
        Path model = temp.resolve("model.txt");
        Files.writeString(model, """
            entity Order
            field id String
            field total int
            """);

        Path generated = MiniMdeGenerator.generate(model, temp.resolve("generated"));
        String code = Files.readString(generated);

        assertTrue(code.contains("record Order"));
        assertTrue(code.contains("String id"));
        assertTrue(code.contains("int total"));
    }

    @Test
    void generatedArtifactReallyCompiles() throws Exception {
        Path model = temp.resolve("model.txt");
        Files.writeString(model, """
            entity Order
            field id String
            field total int
            """);
        Path generated = MiniMdeGenerator.generate(model, temp.resolve("generated"));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        assertNotNull(compiler, "Un JDK est nécessaire, pas seulement un JRE");
        assertEquals(0, compiler.run(null, null, null, generated.toString()));
    }

    @Test
    void rejectsInvalidModel() throws Exception {
        Path model = temp.resolve("bad.txt");
        Files.writeString(model, "not an entity");

        assertThrows(IllegalArgumentException.class,
            () -> MiniMdeGenerator.generate(model, temp.resolve("generated")));
    }
}
