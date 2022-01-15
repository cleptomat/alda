import java.util.*;

public class TelNet {

    private final int  lbg;
    private Map<TelKnoten,Integer> telList;
    private List<TelVerbindung> telverbList;
    private List<TelVerbindung> minSpanTree;

    public TelNet(int lbg){
        this.lbg =lbg;
        telList = new HashMap<TelKnoten,Integer>();
        telverbList = new ArrayList<TelVerbindung>();
    }

    boolean addTelKnoten(int x, int y) {
        var kn = new TelKnoten(x, y);
        if (telList.keySet().contains(kn)) {
            return false;
        }
        for (var k : telList.keySet())
            telverbList.add(new TelVerbindung(k, kn, manhattan(k, kn)));
        telList.put(kn, telList.size());
        return true;
    }

    int manhattan(TelKnoten u, TelKnoten v) {
        var c = Math.abs(u.x - v.x) + Math.abs(u.y - v.y);
        return c <= lbg ? c : Integer.MAX_VALUE;
    }

    public boolean computeOptTelNet(){
        UnionFind forest = new UnionFind(telList.size());
        PriorityQueue<TelVerbindung> edges = new PriorityQueue<TelVerbindung>(1,(a,b) -> a.c - b.c);
        edges.addAll(telverbList);
        minSpanTree = new ArrayList<TelVerbindung>();
        while ( forest.size() != 1 && ! edges.isEmpty() ) {
            TelVerbindung verb = edges.poll();
            if(verb.c <=lbg){
                int t1 = forest.find(telList.get(verb.u) );
                int t2 = forest.find(telList.get(verb.v));
                if (t1 != t2) {
                    forest.union(t1,t2);
                    minSpanTree.add(verb);
                }
            }
        }
        if (forest.size() != 1){
            System.out.println("es existiert kein aufspannender Baum");
            return false;
        } else {
            return true;
        }
    }


    public void drawOptTelNet(int xMax, int yMax){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.setXscale(0,xMax);
        StdDraw.setYscale(0,yMax);
        if(xMax<100){
            for (int x =0;x<=xMax;x++){
                StdDraw.line(x,0,x,yMax);
            }
            for (int y =0;y<=yMax;y++){
                StdDraw.line(0,y,xMax,y);
            }
        }

        for(var elem : minSpanTree){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(elem.v.x,elem.v.y,elem.u.x,elem.v.y);
            StdDraw.line(elem.u.x,elem.v.y,elem.u.x,elem.u.y);

            if(xMax<100) {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(0.03);
                StdDraw.point(elem.u.x, elem.u.y);
                StdDraw.point(elem.v.x, elem.v.y);
            }
        }
        StdDraw.show(0);
    }

    public void generateRandomTelNet(int n, int xMax, int yMax){
        var random = new Random();
        while (n != 0) {
            var x = random.nextInt(xMax) ;
            var y = random.nextInt(yMax);

            if(addTelKnoten(x,y))
                n--;
        }
    }

    public int  getOptTelNetKosten(){
        if(!computeOptTelNet()){
            return -1;
        };
        int s = 0;
        for (var v : minSpanTree)
            s += v.c;
        return s;
    }

    public static void main(java.lang.String[] args){
        var t = new TelNet(10000);

        //t.addTelKnoten(1, 1);
        //t.addTelKnoten(3, 1);
        //t.addTelKnoten(2, 6);
        //t.addTelKnoten(3, 4);
        //t.addTelKnoten(4, 2);
        //t.addTelKnoten(4, 7);
        //t.addTelKnoten(7, 5);

        //System.out.println(t.computeOptTelNet());
        //System.out.println(t.getOptTelNetKosten());
        //System.out.println(t);
        t.generateRandomTelNet(1000,1000,1000);
        t.computeOptTelNet();
        System.out.println(t);
        t.drawOptTelNet(1000,1000);
    }

    public int size(){
        return telList.size();
    }

    @Override
    public java.lang.String toString(){
        var builder = new StringBuilder();
        for (var elem: minSpanTree) {
            builder
                    .append("(")
                    .append(elem.u)
                    .append(",")
                    .append(elem.v)
                    .append(",")
                    .append(elem.c)
                    .append(")")
                    .append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }
}
