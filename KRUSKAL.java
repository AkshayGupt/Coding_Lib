import java.util.*;
import java.io.*;
public class KRUSKAL {

    /**
     * COMPLEXITY: O( mlog(n) )
     */

    static class Edge{
        int a,b;
        long wt;
        Edge(){}
        Edge(int a,int b,long w){
            this.a=a;this.b=b;
            wt=w;
        }
    }

    static class sortEdges implements Comparator<Edge>{
        public int compare(Edge a,Edge b){
            if(a.wt<b.wt) return -1;
            if(a.wt>b.wt) return 1;
            return 0;
        }
    }

    static void union(int par[],int sz[],int a,int b){
        int root_a = root(par,a);
        int root_b = root(par,b);
        if(sz[root_a]>sz[root_b]){
            par[root_b] = par[root_a];
            sz[root_a]+=sz[root_b];
        }
        else{
            par[root_a]=par[root_b];
            sz[root_b]+=sz[root_a];
        }
    }
    static int root(int par[],int i){
        while(par[i] != i){
            par[i] =par[par[i]];
            i=par[i];
        }
        return i;
    }
    static boolean find(int a,int b,int par[]){
       return root(par,a) == root(par,b);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();//no. of vertices
        int m=sc.nextInt();//no. of edges



        int par[]=new int[n+1];
        int sz[]=new int[n+1];

        for(int i=0;i<=n;i++){
            par[i]=i;
            sz[i]=1;
        }

        ArrayList<Edge> edges=new ArrayList<>();
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            long w=sc.nextLong();
            edges.add(new Edge(a,b,w));
        }

        Collections.sort(edges,new sortEdges());

        long sum =0l;
        for(Edge x:edges){
            if(!find(x.a,x.b,par)){
                sum+=x.wt;
                union(par,sz,x.a,x.b);
            }
        }

        /* SUM -> MST TOTAL WT. */

        System.out.println(sum);

    }
}
