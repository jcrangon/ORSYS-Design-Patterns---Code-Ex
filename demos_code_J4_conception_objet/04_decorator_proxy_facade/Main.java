import java.util.*;

public class Main {
    interface ReportService { String get(String id); }
    static final class RealReportService implements ReportService {
        public String get(String id){ System.out.println("[REAL] chargement coûteux"); return "Rapport-"+id; }
    }
    static final class CacheProxy implements ReportService {
        private final ReportService target; private final Map<String,String> cache=new HashMap<>();
        CacheProxy(ReportService target){ this.target=target; }
        public String get(String id){ return cache.computeIfAbsent(id, target::get); }
    }
    static final class LoggingDecorator implements ReportService {
        private final ReportService target;
        LoggingDecorator(ReportService target){ this.target=target; }
        public String get(String id){ System.out.println("[LOG] demande " + id); return target.get(id); }
    }
    static final class ReportFacade {
        private final ReportService reports;
        ReportFacade(ReportService reports){ this.reports=reports; }
        void displayTwice(String id){
            System.out.println("1) " + reports.get(id));
            System.out.println("2) " + reports.get(id));
        }
    }
    public static void main(String[] args){
        ReportService service = new LoggingDecorator(new CacheProxy(new RealReportService()));
        new ReportFacade(service).displayTwice("A42");
        System.out.println("\nÀ commenter : Decorator=journaliser, Proxy=cacher, Facade=simplifier l'orchestration.");
    }
}
