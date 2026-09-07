import java.util.*;

public class Main {
    static long searchLinear(List<Integer> data,int target){
        long t=System.nanoTime();
        for(int x:data) if(x==target) break;
        return System.nanoTime()-t;
    }
    static long searchSet(Set<Integer> data,int target){
        long t=System.nanoTime(); data.contains(target); return System.nanoTime()-t;
    }
    public static void main(String[] args){
        int n=500_000; var list=new ArrayList<Integer>(n);
        for(int i=0;i<n;i++) list.add(i);
        var set=new HashSet<>(list); int target=n-1;
        for(int i=0;i<5;i++){ searchLinear(list,target); searchSet(set,target); }
        long a=0,b=0; for(int i=0;i<20;i++){ a+=searchLinear(list,target); b+=searchSet(set,target); }
        System.out.printf("Moyenne liste : %.3f ms%n",a/20.0/1_000_000.0);
        System.out.printf("Moyenne set   : %.3f ms%n",b/20.0/1_000_000.0);
        System.out.println("À commenter : le spike donne une preuve locale ; il ne remplace pas un benchmark réaliste du système complet.");
    }
}
