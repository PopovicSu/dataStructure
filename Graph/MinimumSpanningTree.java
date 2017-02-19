package Graph;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 * 一个图的最小生成树是将图中所有顶点用这样一组边连起来：这组边的总权值最小。
 */
public class MinimumSpanningTree extends Tree {
    private int totalWeight;

    public MinimumSpanningTree(int root, int[] parent, List<Integer> searchOrders, int totalWeight) {
        super(root, parent, searchOrders);
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

}
