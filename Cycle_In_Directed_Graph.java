static boolean cycle(ArrayList<Integer> [] arr, int n) {
    	
    	boolean vis[]=new boolean[n];
    	boolean stk[]=new boolean[n];
    	
      // 0 -Indexing Nodes considered from 0 to n-1 inclusive.
    	for(int i=0;i<n;i++) {
    		if(!vis[i] && findCycle(i, arr, vis, stk)) {
    			return true;
    		}
    	}
    	
    	return false;
}
    
static boolean findCycle(int nd, ArrayList<Integer> [] arr, boolean [] vis, boolean [] stk) {
    	vis[nd] = stk[nd] = true;
    	
    	for(int ch:arr[nd]) {
    		if(stk[ch]) {
    			return true;
    		}
    		if(!vis[ch] && findCycle(ch, arr, vis, stk)) {
    			return true;
    		}
    	}
    	
    	stk[nd] = false;
    	return false;
}
