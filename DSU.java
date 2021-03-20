import java.util.*;
import java.io.*;
public class DSU {
        
    public static void main(String args[])throws IOException{
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int m=sc.nextInt();

        /* INIT */

        int par[]=new int[n+1];
        int sz[]=new int[n+1];

        for(int i=0;i<=n;i++){
            par[i]=i;
            sz[i]=1;
        }

        for(int i=0;i<m;i++)
        {
            int a=sc.nextInt();
            int b=sc.nextInt();
            union(par,sz,a,b);
        }

        int q=sc.nextInt();
        while(q-->0){
            int a=sc.nextInt();
            int b=sc.nextInt();
            System.out.println(find(a,b,par));
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
}
