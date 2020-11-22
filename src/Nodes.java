package ex1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Nodes implements node_info, Serializable {

    public int key;
    public double Tag;
    public String info;

    public Nodes(int key){
        this.key=key;
        this.setInfo(null);
        this.Tag=0;
    }
    /**
     * Copy constructor
     */
    public Nodes(node_info nd){
        this.key=nd.getKey();
        this.Tag=nd.getTag();
        this.info = nd.getInfo();
    }

    /**
     * return the value of the node_info key
     * @return
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * return this node string info
     * @return
     */
    @Override
    public String getInfo(){
        return this.info;
    }

    /**
     * set new info to this node
     * @param s
     * @return
     */
    @Override
    public void setInfo(String s){
       this.info=s;
    }

    /**
     * return this node tag value
     * @return
     */
    @Override
    public double getTag(){
        return this.Tag;
    }

    /**
     * set new tag to this node
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(double t){
        this.Tag=t;
    }









}
