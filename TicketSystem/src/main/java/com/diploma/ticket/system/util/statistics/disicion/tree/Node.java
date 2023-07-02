package com.diploma.ticket.system.util.statistics.disicion.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String label;
    private boolean isLeaf;
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