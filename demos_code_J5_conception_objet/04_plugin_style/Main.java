import java.util.*;

public class Main {
    interface ExportPlugin { String format(); String export(String data); }
    static final class CsvPlugin implements ExportPlugin {
        public String format(){ return "csv"; }
        public String export(String data){ return "CSV:"+data.replace(" ",","); }
    }
    static final class JsonPlugin implements ExportPlugin {
        public String format(){ return "json"; }
        public String export(String data){ return "{\"value\":\""+data+"\"}"; }
    }
    static final class ExportCore {
        private final Map<String,ExportPlugin> plugins=new HashMap<>();
        void register(ExportPlugin p){ plugins.put(p.format(),p); }
        String export(String format,String data){
            var p=plugins.get(format);
            if(p==null) throw new IllegalArgumentException("format inconnu: "+format);
            return p.export(data);
        }
    }
    public static void main(String[] args){
        var core=new ExportCore(); core.register(new CsvPlugin()); core.register(new JsonPlugin());
        System.out.println(core.export("csv","Alice A101"));
        System.out.println(core.export("json","Alice A101"));
        System.out.println("À commenter : ajouter XML ne nécessite pas de modifier ExportCore.");
    }
}
