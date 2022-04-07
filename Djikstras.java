//**********Djikstra's***************
//(SHORTEST PATH FROM ONE NODE TO ALL NODES)


/*
*
*	Time Complexity: O(E logV) (using heap) ; E updates and each update takes log(V)
*
*/


    static long priority[];
    static class primSort implements Comparator<Integer>{
    	public int compare(Integer a,Integer b) {
    		if(priority[a]>priority[b])
    			return 1;
    		if(priority[a]<priority[b])
    			return -1;
    		return 0;
    	}
    }
    //returns distance of each node from root node; 
    static long[] djiksras(int root,int n,ArrayList<Edge> arr[]) {
    	/*
    	 * i - > dest
    	 * Edge contains dest && cost
    	 */
    	long distance[];
    	int path[];
    	path=new int[n+1];
    	distance=new long[n+1];
    	priority =new long[n+1]; 
    	Arrays.fill(distance, -1l);
    	Arrays.fill(path, -1);
    	PriorityQueue<Integer> pq=new PriorityQueue<>(new primSort());
    	distance[root]=0;
    	pq.add(root);

    	while(!pq.isEmpty()) {
    		int nd = pq.poll();
    		for(int i=0;i<arr[nd].size();i++) {
    			int child =arr[nd].get(i).dest;
    			long edgeWt = arr[nd].get(i).cost;
    			long d = distance[nd]+edgeWt;
    			if(distance[child] == -1) {
    				distance[child]=d;
    				priority[child]=d;
    				pq.add(child);
    				path[child]=nd;    				
    			}
    			if(distance[child]>d) {
    				distance[child]=d;
    				priority[child]=d;
    				path[child]=nd;
    			}
    		}
    	}
    	
    	return distance;
    }   
