import java.util.*;
import java.io.*;

/**
 * PREPROCESSING - O(Nlog(N))
 * QUERY - O(log(N))
 */

public class LCA_binary_lifting{

	static ArrayList<Integer> tree[];
	static int up[][];
	static int log2n;
	static int tin[],tout[],timer;
	
	public static void dfs(int nd,int par) {
		
		tin[nd]=timer++;
		up[nd][0]=par;
		
		for(int i=1;i<=log2n;i++) {
			up[nd][i] =up[up[nd][i-1]][i-1];
		}
		
		for(int ch:tree[nd]) {
			if(ch != par)
			dfs(ch,nd);	
		}
		
		tout[nd]=timer++;
	}
	
	public static boolean isAncestor(int a,int b) {
		// returns true if a is ancestor of b
		return tin[a]<=tin[b] && tout[a]>=tout[b];
	}
	
	public static int lca(int a,int b) {
		if(isAncestor(a,b)) return a;
		if(isAncestor(b,a)) return b;
		
		for(int i=log2n;i>=0;i--) {
			if(!isAncestor(up[a][i],b))
				a=up[a][i];
		}
		
		return up[a][0];
	}
	
	public static void main(String args[]) {
		FastReader sc=new FastReader(); 
		PrintWriter pw=new PrintWriter(System.out);
		
		
		int test=sc.nextInt();
		for(int cs=1;cs<=test;cs++) {
					
				int n=sc.nextInt();
				int m=n-1;
				
				
				/*	Initialization	*/
				
				tree=new ArrayList[n+1];
				for(int i=0;i<=n;i++) tree[i]=new ArrayList<>();
				
				log2n =Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
				up=new int[n+1][log2n+1];
				
				tin =new int[n+1];
				tout =new int[n+1];
				timer=0;
				
				/*	INPUT EDGES	*/
				
				for(int i=0;i<m;i++) {
					int a=sc.nextInt();
					int b=sc.nextInt();
					tree[a].add(b);
					tree[b].add(a);
				}
								
				/*	DFS	*/ 
				
				dfs(1,0);
				tout[0]=timer++;
				
				int q=sc.nextInt();
				
				
				/*	QUERY	*/
				
				while(q-->0) {
					int u=sc.nextInt();
					int v=sc.nextInt();
					pw.println(lca(u,v));
				}
		
		}
		
		pw.close();
		
		
	}
	
	static class FastReader 
	{ 
		BufferedReader br; 
		StringTokenizer st; 

		public FastReader() 
		{ 
			br = new BufferedReader(new
					InputStreamReader(System.in)); 
		} 

		String next() 
		{ 
			while (st == null || !st.hasMoreElements()) 
			{ 
				try
				{ 
					st = new StringTokenizer(br.readLine()); 
				} 
				catch (IOException e) 
				{ 
					e.printStackTrace(); 
				} 
			} 
			return st.nextToken(); 
		} 

		int nextInt() 
		{ 
			return Integer.parseInt(next()); 
		} 

		long nextLong() 
		{ 
			return Long.parseLong(next()); 
		} 

		double nextDouble() 
		{ 
			return Double.parseDouble(next()); 
		} 

		String nextLine() 
		{ 
			String str = ""; 
			try
			{ 
				str = br.readLine(); 
			} 
			catch (IOException e) 
			{ 
				e.printStackTrace(); 
			} 
			return str; 
		} 
	} 
}