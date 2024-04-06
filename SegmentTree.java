
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
/**
Problem: https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/practice-problems/algorithm/help-ashu-1/
**/
public class B {

	static long mod = (long) (1e9 + 7);
	static FastReader sc = new FastReader();

	public static void main(String[] args) {

		StringBuilder ans = new StringBuilder();
		int test = 1;
		while (test-- > 0) {
			
			int n = sc.nextInt();
			int [] arr= new int[n+1];
			for(int i=1; i<=n; i++) {
				arr[i] = sc.nextInt();
			}
			
			int segTree[] = buildSegTree(n, arr);
			
			
			int q = sc.nextInt();
			
			while(q-- > 0) {
				int query = sc.nextInt();
				int l =sc.nextInt(), r=sc.nextInt();
				
				// Query Logic
				switch(query) {
					case 0: update(1, 1, n, l, r, arr, segTree);
						break;
					case 2: ans.append(findOdd(1, 1, n, l, r, arr, segTree)+"\n");
						break;
					case 1: ans.append(((r-l+1) - findOdd(1, 1, n, l, r, arr, segTree))+"\n");
				}
			}
		}

		System.out.print(ans);
	}
	
	static int[] buildSegTree(int n, int[] arr) {
		int tree[] = new int[2*n+2];
		build(1, 1, n, arr, tree);
		
		return tree;
	}
	
	static int build(int node, int l, int r, int[] arr, int[] tree) {
		
		if(l>r) {
			return 0;
		}
		
		if(l == r) {
			//Leaf node
			tree[node]=(arr[l]&1);
			return tree[node];
		}
		
		int mid = l+(r-l)/2;
		int odd1 = build(node*2, l, mid, arr, tree);
		int odd2 = build(node*2+1, mid+1, r, arr, tree);
		
		return tree[node] = odd1+odd2;
	}
	
	static int update(int node, int l, int r, int in, int val, int[]arr, int[] tree) {
		if(l > r) {
			return 0;
		}
		
		if(l == r) {
			arr[in] = val;
			return tree[node] = (arr[in]&1);
		}
		
		int mid = l+(r-l)/2;
		if(in>=l && in<=mid) {
			update(node*2, l, mid, in, val, arr, tree);
		}
		else {
			update(node*2+1, mid+1, r, in, val, arr, tree);
		}
		return tree[node] = (tree[node*2] + tree[node*2+1]);
	}
	
	static int findOdd(int node, int l, int r, int ql, int qr, int[]arr, int[] tree) {
		if(l > r || l > qr || r < ql) {
			return 0;
		}
		
		if(l>=ql && r<=qr) {
			return tree[node];
		}
		
		int mid = l+(r-l)/2;
		return findOdd(node*2, l, mid, ql, qr, arr, tree) +
				findOdd(node*2+1, mid+1, r, ql, qr, arr, tree);	
	}

	static int[] read(int n) {
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		return arr;
	}

	static long add(long a, long b) {
		return (a + b) % mod;
	}

	static long mult(long a, long b) {
		return (a * b) % mod;
	}

	static long _gcd(long a, long b) {
		if (b == 0)
			return a;
		return _gcd(b, a % b);
	}

	static final Random random = new Random();

	static void ruffleSort(int[] a) {
		int n = a.length;// shuffle, then sort
		for (int i = 0; i < n; i++) {
			int oi = random.nextInt(n), temp = a[oi];
			a[oi] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

}
