public class UnionFind {

    private int[] p;
    public UnionFind (int n){
        p = new int[n];
        for (int x = 0;x<n;x++){
            p[x] =-1;
        }
    }

    public int find(int e){
        while (p[e] >= 0) // e ist keine Wurzel
            e = p[e];
        return e;
    }

    public static void main(java.lang.String[] args){
        UnionFind i = new UnionFind(12);
        i.union(0,1);
        i.union(0,2);
        i.union(6,11);
        i.union(6,10);
        i.union(4,7);
        i.union(4,9);
        i.union(6,4);
        i.union(5,8);
        System.out.println(i.find(2));
        System.out.println(i.find(1));
        System.out.println(i.find(9));
        System.out.println(i.find(10));
        System.out.println(i.find(8));


    }

    public int size(){
        int count = 0;
        for (int x = 0;x < p.length;x++){
            if(p[x] < 0){
                count++;
            }
        }
        return count;
    }

    public void union(int s1, int s2){
        unionByHeight(s1,s2);
    }

    private void unionByHeight (int s1, int s2) {
        if (p[s1] >= 0 || p[s2] >= 0)
            return;
        if (s1 == s2)
            return;
        if ( -p[s1] < -p[s2] ) // Höhe von s1 < Höhe von s2
            p[s1] = s2;
        else {
            if ( -p[s1] == -p[s2] )
                p[s1]--; // Höhe von s1 erhöht sich um 1
            p[s2] = s1;
        }
    }
}
