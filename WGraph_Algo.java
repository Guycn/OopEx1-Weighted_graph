package ex1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class WGraph_Algo implements weighted_graph_algorithms, Serializable {
    public weighted_graph Graph;

    public WGraph_Algo() {
        this.Graph = new WGraph_DS();
    }


    public WGraph_Algo(weighted_graph Graph) {
        init(Graph);
    }
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this.Graph = g;
    }
    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return this.Graph;
    }

    @Override
    public weighted_graph copy() {                    //this function make a deep copy of the graph
        weighted_graph copy = new WGraph_DS();
        for (node_info curr : this.Graph.getV()) {                      //The loop passes through all the ver' of the graph
            Nodes t = new Nodes(curr);                                  //create new node
            copy.addNode(t.key);                                        //copy the the old node to the new node
        }
        for (node_info curr0 : this.Graph.getV()) {
            for (node_info curr1 : this.Graph.getV(curr0.getKey())) {            //this loops pass over the all nodes and copy the connection
                double i = this.Graph.getEdge(curr0.getKey(), curr1.getKey());
                if (i != -1) {
                    copy.connect(curr0.getKey(), curr1.getKey(), i);
                }
            }
        }
        return copy;

    }


    @Override
    public boolean isConnected() {
        if (this.Graph.nodeSize() == 0 || this.Graph.nodeSize() == 1) return true;
        int i = this.Graph.edgeSize();
        int k = 0, j = this.Graph.nodeSize() - 2;
        while (j > 0) {
            k = k + j;
            j--;
        }
        if (i > k) return true;
        weighted_graph copyg = copy();
        int Mark = 1;                                      //Set one node with tag=1
        for (node_info curr : copyg.getV()) {              //Zeroed the tags
            if (Mark == 1) {
                curr.setTag(1);
                Mark = 0;
            } else {
                curr.setTag(0);
            }
        }
        for (node_info tmp0 : copyg.getV()) {
            for (node_info tmp1 : copyg.getV(tmp0.getKey())) {                           //Checks if it is possible to reach all the nodes from the marked node
                if ((tmp1.getTag() == 0 && tmp0.getTag() == 1) || (tmp1.getTag() == 1 && tmp0.getTag() == 0)) {
                    tmp0.setTag(1);
                    ;
                    tmp1.setTag(1);
                }
            }
        }
        for (node_info tmp0 : copyg.getV()) {                                //Checks if it is possible to reach all the nodes from the marked node
            for (node_info tmp1 : copy().getV(tmp0.getKey())) {
                if ((tmp1.getTag() == 0 && tmp0.getTag() == 1) || (tmp1.getTag() == 1 && tmp0.getTag() == 0)) {
                    tmp0.setTag(1);
                    tmp1.setTag(1);
                }
            }
        }
        for (node_info check : copyg.getV()) {
            if (check.getTag() == 0) return false;
        }
        return true;

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        int mark = 1;
        for (node_info curr : this.Graph.getV()) {                     //The tag of all vertices is marked with 1
            if (curr.getKey() == src) curr.setTag(0);                  //set the src tag equal to 0
            else curr.setTag(-1);
        }
        for (node_info curr0 : this.Graph.getV(src)) {                 //All src neighbors are marked by the weight between them
            curr0.setTag(this.Graph.getEdge(src, curr0.getKey()));
        }
        while (mark == 1) {
            mark = 0;
            for (node_info curr0 : this.Graph.getV()) {
                for (node_info curr1 : this.Graph.getV(curr0.getKey())) {    // the loops over the all node and set the tag by the weight between them + the neighbors tag(set the min value
                    if (curr0.getTag() != -1) {
                        double i = curr1.getTag();
                        double j = curr0.getTag() + this.Graph.getEdge(curr0.getKey(), curr1.getKey());
                        if (j < i || i==-1) {
                            curr1.setTag(j);
                            mark = 1;
                        }
                    }
                }
            }
        }

        return this.Graph.getNode(dest).getTag();
    }

    /**
     * this function pass through all the ver and starts at the src
     * the function set a path to dest by marking the the info value.
     * if there is shorter way the function remove the old path and marked the short way
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        double i = shortestPathDist(src, dest);
        double j, k, s, mark = 1;
        ArrayList<node_info> Rlist = new ArrayList<>();
        ArrayList<node_info> Llist = new ArrayList<>();
        Rlist.clear();
        Llist.clear();
        j = i;
        for (node_info curr : this.Graph.getV()) {
            curr.setInfo("clear");
        }
        this.Graph.getNode(src).setInfo("tmp");
        Rlist.add(this.Graph.getNode(dest));
        node_info nd = this.Graph.getNode(dest);
        node_info temp = this.Graph.getNode(dest);
        while (j != 0 && j > 0) {
            for (node_info curr0 : this.Graph.getV(nd.getKey())) {
                if (curr0.getInfo() != null) {
                    temp = curr0;
                    k = this.Graph.getEdge(nd.getKey(), curr0.getKey());
                    s = j - k;
                    if ((s - curr0.getTag() < 0.001 && s - curr0.getTag() > -0.001) && nd.getInfo() != null) {
                        Rlist.add(curr0);
                        j = curr0.getTag();
                        curr0.setInfo("tmp");
                        mark = 1;
                        nd = curr0;
                    }
                }
            }
            if (mark == 0) {
                i = j;
                temp.setInfo(null);
                Rlist.clear();
                Rlist.add(this.Graph.getNode(dest));
                mark = 1;
            }
            mark = 0;
        }
        for (int t = (Rlist.size() - 1); t >= 0; t--) {
            Llist.add(Rlist.get(t));
        }
        return Llist;
    }

    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileg = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileg);  //save the graph with fileoutputstream

            out.writeObject(this.Graph);

            out.close();
            fileg.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            FileInputStream fileg = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileg);
            weighted_graph g = (weighted_graph) objectInputStream.readObject();        //load the graph from file
            init(g);
        } catch (Exception e) {
            System.out.println("Exception is caught");
            return false;
        }
        return true;
    }
}













