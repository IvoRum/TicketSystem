package com.diploma.ticket.system.util.statistics.disicion.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pascal Strobel
 * */
public class Node {
    /* Name of the node
    --> if not a leaf node than it's label is the name of the attribute
    --> else its the result/ class value (in our case survived/ not survived)
    */
    private String label;
    // boolean value if node is a leaf node or not
    private boolean isLeaf;
    // List with all successors of the node
    //private List<Node>
    private List<Condition> conditions;

    public Node(){
        conditions = new ArrayList<>();
        isLeaf = false;
    }

    public void addCondition(Condition c){
        this.conditions.add(c);
    }

    public List<Condition> getConditions(){
        return this.conditions;
    }

    public void setLeaf(boolean leaf){
        this.isLeaf = leaf;
    }

    public boolean isLeaf(){
        return this.isLeaf;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}