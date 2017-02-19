package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/2/19.
 */
public class WeightedGraph<V> extends AbstractGraph<V> {
    private List<PriorityQueue<WeightedEdge>> queues;//带权图的邻接表存储的是边结点
    protected WeightedGraph(int[][] edges, V[] vertices) {
        //边由二维数组存储
        super(edges, vertices);
        createQueues(edges, vertices.length);
    }

    private void createQueues(int[][] edges, int length) {
        queues = new ArrayList<PriorityQueue<WeightedEdge>>();
        for(int i=0;i<length;i++){
            queues.add(new PriorityQueue<WeightedEdge>());
        }
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            queues.get(u).offer(new WeightedEdge(u, v, w));
        }
    }

    protected WeightedGraph(List<WeightedEdge> edges, List<V> vertices) {
        //边用List存储
        super((List)edges, vertices);
        createQueues(edges, vertices.size());
    }
    private void createQueues(List<WeightedEdge> edges, int length) {
        queues = new ArrayList<PriorityQueue<WeightedEdge>>();
        for(int i=0;i<length;i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }
        for(int i=0;i<edges.size();i++){
            WeightedEdge edge = edges.get(i);
            queues.get(edge.u).offer(edge);
        }
    }
    public void printWeightedEdges(){
        for(int i=0;i<queues.size();i++){
            System.out.print("Vertex "+i+"：");
            for(WeightedEdge edge:queues.get(i)){
                System.out.print("("+edge.u+","+edge.v+","+edge.weight+")");
            }
            System.out.println();
        }
    }
    private List<PriorityQueue<WeightedEdge>> deepClone(List<PriorityQueue<WeightedEdge>> queues){
        List<PriorityQueue<WeightedEdge>> copiedQueue = new ArrayList<PriorityQueue<WeightedEdge>>();
        for(int i=0;i<queues.size();i++){
            copiedQueue.add(new PriorityQueue<WeightedEdge>());
            for (WeightedEdge edge : queues.get(i)) {
                copiedQueue.get(i).add(edge);
            }
        }
        return copiedQueue;
    }
    public MinimumSpanningTree getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }
    public MinimumSpanningTree getMinimumSpanningTree(int startIndex){
        List<Integer> T=new ArrayList<Integer>();//每个被找到的合适的边的结点放在这里
        T.add(startIndex);
        int numberOfVertices=vertices.size();
        int[] parent = new int[numberOfVertices];
        for(int i=0;i<parent.length;i++){
            parent[i]=-1;
        }
        int totalWeight=0;

        //clone the queue so as to keep the original queue intact
        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);
        //all vertices are found?
        while (T.size() < numberOfVertices) {
            //寻找从startIndex开始的最小的权值的边的顶点
            int v=-1;
            double smallestWeight=Double.MAX_VALUE;
            for(int u:T){
                while (queues.get(u).isEmpty() == false && T.contains(queues.get(u).peek().v)) {
                    queues.get(u).remove();
                }
                if (queues.get(u).isEmpty()) {
                    continue;
                }
                WeightedEdge edge=queues.get(u).peek();
                if (edge.weight < smallestWeight) {
                    v=edge.v;
                    smallestWeight=edge.weight;
                    parent[v]=u;
                }
            }
            T.add(v);
            totalWeight+=smallestWeight;
        }
        return new MinimumSpanningTree(startIndex, parent, T, totalWeight);
    }

}
