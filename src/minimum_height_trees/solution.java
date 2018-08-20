class Solution {
    /**
     * Solution Explanation
     * ============================
     * First notice that the input format reveals that the given graph will always be 
     * connected and acyclic, which tells us that every node can be reached from every 
     * other node within the graph. Now using this fact, it can be deduced that the 
     * height of a tree rooted at some node n will simply be the longest distance from 
     * node n to any other node in the graph. Thus, the trees of minimum height will be 
     * the trees rooted at the nodes that are closest to all other nodes in the graph. 
     * For any acyclic and connected graph, clearly the node(s) closest to all other 
     * nodes in the graph will be those at the middle of the longest path in the graph.
     * Therefore, solving the problem reduces to finding the set of nodes in the middle
     * of the longest path in the tree. This problem can be solved using the following
     * algorithm:
     *   
     *      1. Start with a list of pointers to every node of degree 1 in the 
     *          initial graph called leaves.
     *      3. While the size of leaves is greater than 2 do the following:
     *          i) Create a new list called newLeaves.
     *          ii) For every node pointer, currLeaf, in leaves do the following:
     *              a) Get any neighbor, potentialLeaf, of currLeaf.
     *              b) Remove currLeaf from the list of neighbors for potentialLeaf.
     *              c) If the degree of potentialLeaf is 1, then add potentialLeaf
     *                  to newLeaves.
     *          iii) Set leaves to now be newLeaves.
     *      4. The final answer will be the nodes pointed to by the last one/two 
     *          pointer(s) in leaves.
     * 
     * NOTE: There are two special cases arising from unique inputs that need to be taken
     * into account:
     *      1. n = 0 => Return an empty list.
     *      2. n = 1 => Return a list with only the node n = 0 in it.
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Time Complexity:  O(n) => The algorithm's runtime is dominated by the traversal 
     * over the graph represented in the while loop. The traversal will visit every 
     * single edge in the graph giving O(|E|); however, for any connected and acyclic
     * graph we have that |E| = |V| - 1 = n - 1. Therefore, the runtime will be O(n).
     * Space Complexity: O(n) => The algorithm's main storage is dominated by storing
     * the adjancency sets representing the graph, which will be of size 2 * |E|, or 
     * 2 * (n - 1). Therefore, the space complexity will be O(n).
     * ============================
     */

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Handle special cases
        if(n < 1) return new ArrayList<Integer>();
        if(n < 2) return Collections.singletonList(0);
        // Create the adjacency representation for the graph.
        List<Set<Integer>> adjancencySets = new ArrayList<>();
        for(int i = 0; i < n; i++) adjancencySets.add(new HashSet<>());
        for(int[] edge : edges) {
            adjancencySets.get(edge[0]).add(edge[1]);
            adjancencySets.get(edge[1]).add(edge[0]);
        }
        // Initialize the list of leaves being tracked.
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(adjancencySets.get(i).size() == 1) leaves.add(i);
        }
        /**
         * Iterativley move leaf pointers one edge forward, and
         * then trim the leaf from the resulting graph. Continue
         * this process until either 1 or 2 leafs are in the list
         * of leaves.
         */
        while(n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(int currLeaf : leaves) {
                // Pick the node to move to from the current leaf,
                // and remove the old leaf from the graph.
                int potentialLeaf = adjancencySets.get(currLeaf).iterator().next();
                adjancencySets.get(potentialLeaf).remove(currLeaf);
                // Add the new leaf to the leaf set if it now has 
                // degree 1 after removing the old leaf.
                if(adjancencySets.get(potentialLeaf).size() == 1) {
                    newLeaves.add(potentialLeaf);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

}
