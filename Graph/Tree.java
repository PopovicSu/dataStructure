package Graph;

import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
public class Tree {
    private int root;//store the root of tree
    private int[] parent;//store parent of each vertex
    private List<Integer> searchOrders;

    public Tree(int root,int[] parent,List<Integer> searchOrders){
        this.root=root;
        this.parent=parent;
        this.searchOrders=searchOrders;
    }

    public Tree(int root, int[] parent) {
        this.root=root;
        this.parent=parent;
    }
    public int getRoot(){
        return root;
    }
    public int getParent(int v){
        return parent[v];
    }
    public List<Integer> getSearchOrders(){
        return searchOrders;
    }
    public int getNumbersOfVerticesFound(){
        return searchOrders.size();
    }

}
