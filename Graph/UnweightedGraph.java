package Graph;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 * 非加权图
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {

    public UnweightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
    }

    public UnweightedGraph(List<Edge> edges, List<V> vertices) {
        super(edges, vertices);
    }
}
