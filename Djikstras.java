//**********Djikstra's***************
//(SHORTEST PATH FROM ONE NODE TO ALL NODES)


/*
*
*	Time Complexity: O(E logV) (using heap) ; E updates and each update takes log(V)
*
*/
/*

*/


// User function Template for Java
class Solution {

    
    class iPair {
        int first, second;
    
        iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    // Function to find the shortest distance of all the vertices
    // from the source vertex src.
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        // Write your code here
        int n = adj.size();
        int dist[] = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        
        PriorityQueue<iPair> nodes = new PriorityQueue<>((a, b) -> a.second-b.second);
        
        nodes.add(new iPair(src, 0));
        
        while(!nodes.isEmpty()) {
            iPair nd = nodes.poll();
            if(dist[nd.first] < nd.second) continue; //Redundant node
            
            for(iPair ch: adj.get(nd.first)) {
                if(dist[ch.first] == -1 || dist[ch.first] > dist[nd.first] + ch.second) {
                    dist[ch.first] = dist[nd.first]+ch.second;
                    nodes.add(new iPair(ch.first, dist[ch.first])); 
                    // If a node is already present in heap, then this would add another node but doesnot matter as it will rebalance the tree and would return the shortest distance node.
                    // Why not simply update the second paramter ? - this won't help as
                    // Java's PriorityQueue is a binary heap that does not support a decrease-key operation (which is an efficient way to update a key's priority in data structures like Fibonacci Heaps).
                    // PriorityQueue only reorders when you explicitly remove an element and reinsert it.
                }
            }
        }
        
        final ArrayList<Integer> li = new ArrayList<>(n);
        for(int d: dist) {
            li.add(d);
        }
        
        return li;
        
    }
}
