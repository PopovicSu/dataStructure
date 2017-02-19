package Graph;

/**
 * Created by Administrator on 2017/2/19.
 * //加权图的顶点的定义
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
    public int weight;
    public WeightedEdge(int u, int v,int weight) {
        super(u, v);
        this.weight=weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        //定义比较的方法，用以排列优先级.
        if (weight > o.weight) {
            return 1;
        } else if (weight < o.weight) {
            return -1;
        }
        return 0;
    }
}
