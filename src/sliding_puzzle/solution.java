class Solution {
    /**
     * Solution Explanation
     * ============================
     * First consider the process of moving from one board configuration to another.
     * Possible transitions for the machine are dependent on the current state 
     * represented by the tiles at each board position, (i, j, k, m, n, l) for positions
     *  0 through 5. Now in order to solve the problem, the solution will perform a BFS
     * from the initial position given. At each step of the search, if a state hasn't
     * been seen before, then a map that contains the distance for the state will be 
     * updated to reflect the number of moves taken before reaching the state. In this
     * way, it can be concluded that once every state has been processed the map will
     * contain the shortest number of moves to get to that state from the initial 
     * position. The basic algorithm is as follows:
     * 
     *      1. Initialize an empty Map of <String, Integer> pairs called distances.
     *      2. Initialize an empty queue with the initial state represented as a string.
     *      3. Put the distance as 0 for the initial state in the distances map.
     *      4. Now, while the queue is not empty do the following:
     *          i) Let currState be the result of removing the front of the queue.
     *          ii) Determine all possible valid transitions from currState.
     *          iii) For each of the possible transitions:
     *              a) If the state hasn't been seen before then add it to the queue, and
     *                  set the key for the state in distances to have the value of the 
     *                  distance for currState + 1.
     *      5. If the value for the final state is contained within the map distances, 
     *          then return it. Otherwise, return -1.
     * 
     * ============================
     *
     * Solution Complexities
     * ============================
     * Time Complexity:  O(1) => At most, the algorithm will visit every possible state
     * for the board. Since the board is always a 2x3 matrix, the number of states can 
     * be enumerated easily as 6!, or 720, different states. If every state is visited at
     * most once for a given input, then the runtime will be O(720) = O(1).
     * Space Complexity: O(1) => Using similar reasoning as the time complexity, the map
     * or queue will, at any given point during execution, have at most 720 different 
     * values, one for each state. Thus, the overall space complexity will be O(720) = 
     * O(1).
     * ============================
     */

    public int slidingPuzzle(int[][] board) {
        // Initialize BFS structures
        String targetState = "123450";
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        String initialState = boardState(board);
        distances.put(initialState, 0);
        q.add(initialState);
        
        // Run BFS on the board transitions
        while(!q.isEmpty()) {
            String currState = q.remove();
            List<String> newStates = possibleTransitions(currState);
            for(String newState : newStates) {
                // Update all the distances for all states not already seen
                if(!distances.containsKey(newState)) {
                    q.add(newState);
                    distances.put(newState, distances.get(currState) + 1);
                } 
            }
        }
        return distances.getOrDefault(targetState, -1);
    }
    
    public List<String> possibleTransitions(String state) {
        List<String> result = new ArrayList<>();
        int pos = state.indexOf('0');
        if(pos != 0 && pos != 3) result.add(swap(pos, pos - 1, state));
        if(pos != 2 && pos != 5) result.add(swap(pos, pos + 1, state));
        result.add(swap(pos, (pos + 3) % 6, state));
        return result;
    }
    
    public String swap(int i, int j, String s) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
    
    public String boardState(int[][] board) {
        StringBuilder s = new StringBuilder();
        for(int[] row : board) {
            for(int val : row) s.append(val);
        }
        return s.toString();
    }

}
