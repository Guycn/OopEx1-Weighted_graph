package ex1;


import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {
    public HashMap<Integer, node_info> graph=new HashMap<>();
    public HashMap<Integer, HashMap<Integer,Double>> weight = new HashMap<>();     //Neighbors hashmap
    public int Vco=0;
    public int Eco=0;
    public int MC=0;

    

    @Override
    public node_info getNode(int key) {
        if(this.graph.containsKey(key)){  //Check if vertex exist
            return this.graph.get(key);
        }
        return null;
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2) return true;          //node1 and node2 is the same ver
        if(this.weight.get(node1) == null || this.weight.get(node2) == null)return false;  //check if the 2 nodes are exist
        if (this.weight.get(node1).containsKey(node2) && this.weight.get(node2).containsKey(node1))   //check the connection
            return true;
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {
        if(this.weight.get(node1)==null || this.weight.get(node1).get(node2)==null)return -1;   //check if the 2 nodes are exist
        if(hasEdge(node1,node2))return this.weight.get(node1).get(node2);                       //return the weight between the 2 nodes
        return -1;
    }

    @Override
    public void addNode(int key) {
        if(!this.graph.containsKey(key)) {   //Check if the node exist
            Nodes t = new Nodes(key);        //create new node_info
            this.graph.put(key, t);
            Vco++;
            MC++;
        }

    }

    /**
     * Connect edge between node1 and node2 and set the weight w between them
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (node1 == node2) return;                                                    //node1 and node 2 is the same node
        if (!this.graph.containsKey(node1) || !this.graph.containsKey(node2))return;   //check if the graph contain the nodes
        if(this.weight.get(node1)==null){
            HashMap<Integer,Double> add0 = new HashMap<>();                            //check null
            this.weight.put(node1,add0);
        }
        if(this.weight.get(node2)==null){
            HashMap<Integer,Double> add1 = new HashMap<>();
            this.weight.put(node2,add1);
        }
        if(this.weight.get(node1).containsKey(node2) && this.weight.get(node2).containsKey(node1))return;  //return if the connection between the 2 nodes are exist
        this.weight.get(node1).put(node2,w);              //create the connection
        this.weight.get(node2).put(node1,w);              //create the connection
        Eco++;                                            //Eco is edge counter
        MC++;
    }

    @Override
    public Collection<node_info> getV() {
        return this.graph.values();
    }            //return the the hashmap values (collection of node_info) of the ver

    @Override
    public Collection<node_info> getV(int node_id) {
        ArrayList<node_info> Coll = new ArrayList<>();            //create new ArrayList
        int i;
        for(i=0;i<this.graph.size();i++){
            if(this.graph.get(i)!=null){
                if(this.hasEdge(i,node_id)){                       //check if the connection between the 2 nodes are exist
                    if(i!=node_id)Coll.add(this.graph.get(i));     //Create new list with all the node_id neighbors
                }
            }
        }
        return Coll;
    }

    /**
     * The function remocve node check if the node exist
     * if the node exist the function remove the connection between the node and his neighbors
     * and than remove the node
     *
     * @param key
     * @return
     */
    @Override
    public node_info removeNode(int key) {
        if(this.graph==null)return null;                //check that the graph not point to null
        if(!this.graph.containsKey(key))return null;    //check that the node exist
        int i=0,j=0,size;
        if(this.weight.get(key)!=null) {           //check if the node hae connection with other nodes
            size = this.weight.get(key).size();    //check how many neighbors the node had
            while (i < size) {                     //this loop remove the all connections
                if (this.weight.get(key).containsKey(j)) {
                    this.weight.get(key).remove(j);
                    this.weight.get(j).remove(key);
                    this.Eco--;
                    MC++;
                    j++;
                    i++;
                } else {
                    j++;
                }
            }
        }
        Nodes t = new Nodes(this.graph.get(key));       //copy the node
        this.graph.remove(key);
        MC++;
        Vco--;
        return t;          //return copy of the node
    }

    /**
     * if node1 and node 2 are connected this function remove the edge between this 2 nodes
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if(this.weight.get(node1)!=null) this.weight.get(node1).remove(node2);
        if(this.weight.get(node2)!=null) this.weight.get(node2).remove(node1);
        Eco--;
        MC++;
    }

    /**
     * return the number of the nodes in the graph
     * @return
     */
    @Override
    public int nodeSize() {
        return this.Vco;
    }

    /**
     * return the the number of the edges
     * @return
     */
    @Override
    public int edgeSize() {
        return this.Eco;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
