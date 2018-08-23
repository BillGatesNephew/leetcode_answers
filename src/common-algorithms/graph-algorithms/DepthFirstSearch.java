import java.util.*;

class DepthFirstSearch {

    /**
     * Performs a depth first search on a graph that's represented in the form
     * of an adjacency matrix with where index (i, j) is 1 if an edge exists between 
     * vertex i and vertex j. 
     * @param graph - {@code int[][]} - The graph represented as an adjacency matrix.
     * @return A {@code List<List<Integer>>} representing the order in which the vertices 
     * were visited for every connnected component of the input graph.
     */
    public static List<List<Integer>> adjacencyMatrixDFS(int[][] graph) {
        // Order in which the vertices were visited for every connected component
        List<List<Integer>> visitOrder = new ArrayList<>();
        // The vertices left to visit
        Stack<Integer> verticesToVisit = new Stack<>();
        // Indicators for whether or not a given vertex has been visited
        boolean[] vertexVisited = new boolean[graph.length];

        // Start a search from every vertex to ensure each connected component of the 
        // graph has been traversed
        for(int searchRoot = 0; searchRoot < graph.length; searchRoot++) {
            // Ignore searches starting at already visited vertices to prevent traversing
            // a connected component of the original graph twice
            if(vertexVisited[searchRoot]) continue;
            List<Integer> currComponentVisitOrder = new ArrayList<>();

            // Add the root of the traversal to the stack, and mark the root as visited
            verticesToVisit.push(searchRoot);
            vertexVisited[searchRoot] = true;
            currComponentVisitOrder.add(searchRoot);

            // Execute the search until there are no more vertices left to visit in the 
            // current connected component 
            while(!verticesToVisit.isEmpty()) {
                // Get the next vertex to move from
                int currVertex = verticesToVisit.pop();
                // Add the non-visited neighbors of the currVertex 
                for(int neighbor = 0; neighbor < graph[currVertex].length; neighbor++) {
                    if(graph[currVertex][neighbor] == 1 && !vertexVisited[neighbor]) {
                        verticesToVisit.push(currVertex);
                        verticesToVisit.push(neighbor);
                        vertexVisited[neighbor] = true;
                        currComponentVisitOrder.add(neighbor);
                        break;
                    }
                }
            }
            visitOrder.add(currComponentVisitOrder);
        }
        return visitOrder;
    }

    /**
     * Performs a depth first search on a graph that's represented in the form of an
     * adjacency list with where index i of the graph input is a Set that contains all of
     * the neighbors of vertex i. 
     * @param graph - {@List<Set<Integer>>} - The graph represented as an adjacency list.
     * @return A {@code List<List<Integer>>} representing the order in which the vertices 
     * were visited for every connnected component of the input graph.
     */
    public static List<List<Integer>> adjacencyListDFS(List<Set<Integer>> graph) {
        // Order in which the vertices were visited for every connected component
        List<List<Integer>> visitOrder = new ArrayList<>();
        // The vertices left to visit
        Stack<Integer> verticesToVisit = new Stack<>();
        // Indicators for whether or not a given vertex has been visited
        boolean[] vertexVisited = new boolean[graph.size()];

        // Start a search from every vertex to ensure each connected component of the 
        // graph has been traversed
        for(int searchRoot = 0; searchRoot < graph.size(); searchRoot++) {
            // Ignore searches starting at already visited vertices to prevent traversing
            // a connected component of the original graph twice
            if(vertexVisited[searchRoot]) continue;
            List<Integer> currComponentVisitOrder = new ArrayList<>();

            // Add the root of the traversal to the stack, and mark the root as visited
            verticesToVisit.push(searchRoot);
            vertexVisited[searchRoot] = true;
            currComponentVisitOrder.add(searchRoot);

            // Execute the search until there are no more vertices left to visit in the 
            // current connected component 
            while(!verticesToVisit.isEmpty()) {
                // Get the next vertex to move from
                int currVertex = verticesToVisit.pop();
                // Add the non-visited neighbors of the currVertex 
                Iterator<Integer> neighborIterator = graph.get(currVertex).iterator();
                while(neighborIterator.hasNext()) {
                    int neighbor = neighborIterator.next();
                    if(!vertexVisited[neighbor]) {
                        verticesToVisit.push(currVertex);
                        verticesToVisit.push(neighbor);
                        vertexVisited[neighbor] = true;
                        currComponentVisitOrder.add(neighbor);
                        break;
                    }
                }
            }
            visitOrder.add(currComponentVisitOrder);
        }
        return visitOrder;
    }

    public static void printVisitOrder(List<List<Integer>> visitOrder) {
        for(int component = 0; component < visitOrder.size(); component++) {
            System.out.println("Component " + (component + 1) + ": ");
            List<Integer> compOrder = visitOrder.get(component);
            for(int i = 0; i < compOrder.size(); i++) {
                if(i != 0) System.out.print(" -> ");
                System.out.print(compOrder.get(i));
            }
            System.out.println("\n----------------------------");
        }
    }
    
    
    public static void main(String[] args) {
        /**
         *      0           5 
         *      |          | \   
         *      1          |  7
         *    /  \         | /
         *   2    3        6
         *   \   /
         *     4
         */
        int[][] adjMatrix = {
            /*    Vertex Indicies     */
            /* 0  1  2  3  4  5  6  7 */
              /* Component 1 */
              {0, 1, 0, 0, 0, 0, 0, 0}, // Vertex 0
              {1, 0, 1, 1, 0, 0, 0, 0}, // Vertex 1
              {0, 1, 0, 0, 1, 0, 0, 0}, // Vertex 2
              {0, 1, 0, 0, 1, 0, 0, 0}, // Vertex 3
              {0, 0, 1, 1, 0, 0, 0, 0}, // Vertex 4
              /* Component 2 */
              {0, 0, 0, 0, 0, 0, 1, 1}, // Vertex 5
              {0, 0, 0, 0, 0, 1, 0, 1}, // Vertex 6
              {0, 0, 0, 0, 0, 1, 1, 0} // Vertex 7
          };

          Graph graph = new Graph(adjMatrix);

          System.out.println("Matrix Order");
          List<List<Integer>> matrixOrder = adjacencyMatrixDFS(adjMatrix);
          printVisitOrder(matrixOrder);

          System.out.println("List Order");
          List<List<Integer>> listOrder = adjacencyListDFS(graph.getAdjacencyList());
          printVisitOrder(listOrder);
      }
}