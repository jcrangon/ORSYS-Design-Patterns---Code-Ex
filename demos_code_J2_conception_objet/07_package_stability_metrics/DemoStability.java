import java.util.List;

public class DemoStability {
    record PackageStats(String name, int ca, int ce) {
        double instability() {
            return (ca + ce) == 0 ? 0.0 : (double) ce / (ca + ce);
        }
    }

    public static void main(String[] args) {
        List<PackageStats> packages = List.of(
                new PackageStats("domain", 8, 1),
                new PackageStats("application", 4, 4),
                new PackageStats("stripe-adapter", 0, 6),
                new PackageStats("shared", 11, 9)
        );

        System.out.printf("%-16s %4s %4s %8s  %s%n", "package", "Ca", "Ce", "I", "lecture");
        for (PackageStats p : packages) {
            double i = p.instability();
            String reading = i < .3 ? "stable" : i > .7 ? "instable" : "intermédiaire";
            System.out.printf("%-16s %4d %4d %8.2f  %s%n", p.name(), p.ca(), p.ce(), i, reading);
        }
    }
}
