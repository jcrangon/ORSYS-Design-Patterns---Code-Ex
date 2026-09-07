import java.util.*;

public class DemoArchitectureRules {
    record Dependency(String from, String to) { }

    static final Set<String> FORBIDDEN_FOR_DOMAIN = Set.of("infrastructure", "web", "stripe");

    static void assertRules(List<Dependency> deps) {
        for (Dependency d : deps) {
            if (d.from().equals("domain") && FORBIDDEN_FOR_DOMAIN.contains(d.to())) {
                throw new AssertionError("Règle violée : domain ne doit pas dépendre de " + d.to());
            }
        }
        assertAcyclic(deps);
    }

    static void assertAcyclic(List<Dependency> deps) {
        Map<String, List<String>> graph = new HashMap<>();
        for (Dependency d : deps) graph.computeIfAbsent(d.from(), k -> new ArrayList<>()).add(d.to());
        Set<String> visiting = new HashSet<>(), visited = new HashSet<>();
        for (String node : graph.keySet()) dfs(node, graph, visiting, visited);
    }

    static void dfs(String node, Map<String, List<String>> g, Set<String> visiting, Set<String> visited) {
        if (visiting.contains(node)) throw new AssertionError("Cycle détecté autour de " + node);
        if (!visited.add(node)) return;
        visiting.add(node);
        for (String next : g.getOrDefault(node, List.of())) dfs(next, g, visiting, visited);
        visiting.remove(node);
    }

    public static void main(String[] args) {
        List<Dependency> valid = List.of(
                new Dependency("web", "application"),
                new Dependency("application", "domain"),
                new Dependency("infrastructure", "application")
        );
        assertRules(valid);
        System.out.println("Architecture valide ✓");

        List<Dependency> invalid = List.of(
                new Dependency("domain", "stripe"),
                new Dependency("application", "domain")
        );
        try {
            assertRules(invalid);
        } catch (AssertionError e) {
            System.out.println("Architecture refusée ✗ : " + e.getMessage());
        }

        List<Dependency> cyclic = List.of(
                new Dependency("orders", "billing"),
                new Dependency("billing", "orders")
        );
        try {
            assertRules(cyclic);
        } catch (AssertionError e) {
            System.out.println("Cycle refusé ✗ : " + e.getMessage());
        }
    }
}
