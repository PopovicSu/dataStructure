package Graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices;//store vertices
    protected List<List<Integer>> neighbors;//store adjacency lists

    /*construct a graph from edges and vertices stored in arrays*/
    protected AbstractGraph(int[][] edges, V[] vertices) {
        this.vertices = new ArrayList<V>();
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
        this.vertices = vertices;
        createAdjacencyLists(edges, vertices.size());
    }

    private void createAdjacencyLists(int[][] edges, int numberOfVertics) {
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertics; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
        return;
    }

    private void createAdjacencyLists(List<Edge> edges, int numberOfVertics) {
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertics; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            neighbors.get(edge.u).add(edge.v);
        }
    }

    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertics() {
        return vertices;
    }

    @Override
    public V getVertex(int v) {
        return vertices.get(v);
    }

    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    public int[][] getAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[getSize()][getSize()];
        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < neighbors.get(i).size(); j++) {
                int v = neighbors.get(i).get(j);
                adjacencyMatrix[i][j] = 1;
            }
        }
        return adjacencyMatrix;
    }

    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print("Vertex" + " +" + u + ":");
            for (int i = 0; i < neighbors.get(u).size(); i++) {
                System.out.print("(" + u + "," + neighbors.get(u).get(i) + ")");
            }
            System.out.println();
        }
    }

    public Tree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        //mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];
        dfs(v, parent, searchOrders, isVisited);
        return new Tree(v, parent, searchOrders);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited) {
        searchOrders.add(v);
        isVisited[v] = true;
        for (int i :neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v;
                dfs(i, parent, searchOrders, isVisited);
            }
        }
    }

    public Tree bfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);//add the element to the tail of queue
        isVisited[v] = true;
        while (queue.isEmpty() == false) {
            int u = queue.poll();//检索并删除
            searchOrders.add(u);
            for (int i = 0; i < neighbors.get(u).size(); i++) {
                int next = neighbors.get(u).get(i);
                if (isVisited[next] == false) {
                    queue.offer(next);
                    parent[next] = u;
                    isVisited[next] = true;
                }
            }
        }
        return new Tree(v, parent, searchOrders);
    }

}
