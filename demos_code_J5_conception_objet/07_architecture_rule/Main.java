import java.util.*;

public class Main {
    record Dependency(String fromPackage,String toPackage) {}
    static List<String> violations(List<Dependency> deps){
        var out=new ArrayList<String>();
        for(var d:deps){
            if(d.fromPackage().startsWith("app.domain") && d.toPackage().startsWith("app.infrastructure"))
                out.add(d.fromPackage()+" -> "+d.toPackage());
        }
        return out;
    }
    public static void main(String[] args){
        var graph=List.of(
            new Dependency("app.web","app.application"),
            new Dependency("app.application","app.domain"),
            new Dependency("app.infrastructure.sql","app.domain"),
            new Dependency("app.domain.booking","app.infrastructure.sql")
        );
        var bad=violations(graph);
        System.out.println("Violations = " + bad);
        if(!bad.isEmpty()) System.out.println("CI: FAILED - le domaine dépend de l'infrastructure");
        System.out.println("À commenter : une règle documentée seulement dans un wiki se dégrade silencieusement.");
    }
}
