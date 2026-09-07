import java.util.*;

public class Main {
    interface Node { void accept(Visitor v); }
    static final class FileNode implements Node {
        final String name; final int size;
        FileNode(String n,int s){ name=n; size=s; }
        public void accept(Visitor v){ v.visit(this); }
    }
    static final class Folder implements Node {
        final String name; final List<Node> children=new ArrayList<>();
        Folder(String n){ name=n; }
        Folder add(Node n){ children.add(n); return this; }
        public void accept(Visitor v){ v.visit(this); for(Node n:children) n.accept(v); }
    }
    interface Visitor { void visit(FileNode f); void visit(Folder d); }
    static final class SizeVisitor implements Visitor {
        int total=0;
        public void visit(FileNode f){ total += f.size; System.out.println("file " + f.name + " size="+f.size); }
        public void visit(Folder d){ System.out.println("folder " + d.name); }
    }
    public static void main(String[] args){
        var root=new Folder("root").add(new FileNode("a.txt",10)).add(new Folder("images").add(new FileNode("x.png",90)));
        var visitor=new SizeVisitor(); root.accept(visitor);
        System.out.println("Total="+visitor.total);
        System.out.println("À commenter : ajouter une opération Visitor n'oblige pas à modifier FileNode/Folder.");
    }
}
