import java.util.*;

class Graph {
    
    private List<Set<Integer>> vertexNeighbors;

    Graph() {
        this.vertexNeighbors = new ArrayList<>();
    }

    Graph(int[][] initialGraph) {
        this.vertexNeighbors = new ArrayList<>();
        for(int vertex = 0; vertex < initialGraph.length; vertex++) {
            Set<Integer> neighbors = new HashSet<>();
            for(int neighbor = 0; neighbor < initialGraph[vertex].length; neighbor++) {
                if(initialGraph[vertex][neighbor] == 1) neighbors.add(neighbor);
            }
            vertexNeighbors.add(neighbors);
        }
    }

    public boolean addEdge(int startVertex, int endVertex) {
        if(invalidVertex(startVertex) || invalidVertex(endVertex)) return false;
        return vertexNeighbors.get(startVertex).add(endVertex);
    }

    public boolean removeEdge(int startVertex, int endVertex) {
        if(invalidVertex(startVertex) || invalidVertex(endVertex)) return false;
        return vertexNeighbors.get(startVertex).remove(endVertex);
    }

    public boolean removeVertex(int removedVertex) {
        if(invalidVertex(removedVertex)) return false;
        // Remove the vertex from all the neighbor sets and update the vertex numbers
        for(int vertex = 0; vertex < vertexNeighbors.size(); vertex++) {
            Set<Integer> neighbors = vertexNeighbors.get(vertex);
            // Remove the vertex being removed
            neighbors.remove(removedVertex);
            // Decrement all vertex indices larger than the vertex being removed
            Iterator<Integer> neighborIterator = neighbors.iterator();
            while(neighborIterator.hasNext()) {
                int neighbor = neighborIterator.next();
                if(neighbor > removedVertex) {
                    neighbors.remove(neighbor);
                    neighbors.add(neighbor - 1);
                }
            }
        }
        vertexNeighbors.remove(removedVertex);
        return true;
    }

    public int addVertex() {
        vertexNeighbors.add(new HashSet<>());
        return vertexNeighbors.size() - 1;
    }

    public Set<Integer> getVertexNeighbors(int vertex) {
        if(invalidVertex(vertex)) return null;
        return new HashSet<>(vertexNeighbors.get(vertex));
    }

    public int getVertexDegree(int vertex) {
        if(invalidVertex(vertex)) return -1;
        return vertexNeighbors.get(vertex).size();
    }

    public int[][] getAdjacencyMatrix() {
        int[][] adjMatrix = new int[vertexNeighbors.size()][vertexNeighbors.size()];
        for(int vertex = 0; vertex < vertexNeighbors.size(); vertex++) {
            for(int neighbor = 0; neighbor < vertexNeighbors.size(); neighbor++) {
                int status = vertexNeighbors.get(vertex).contains(neighbor) ? 1 : 0; 
                adjMatrix[vertex][neighbor] = status;
            }
        }
        return adjMatrix;
    }

    public List<Set<Integer>> getAdjacencyList() {
        List<Set<Integer>> adjList = new ArrayList<>();
        for(Set<Integer> neighbors : vertexNeighbors) {
            adjList.add(new HashSet<>(neighbors));
        }
        return adjList;
    }

    private boolean invalidVertex(int vertex) {
        return vertex < 0 || vertex >= vertexNeighbors.size();
    }
}
