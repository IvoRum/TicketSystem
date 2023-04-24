package com.diploma.ticket.system.util.statistics.disicion.tree;

public class Condition {

    // successor = Nachfolger
    private Node successor;
    private int compareValue;

    public Condition(int value){
        this.compareValue = value;
    }

    public int getCompareValue(){
        return this.compareValue;
    }

    public boolean check(int value){
        return compareValue == value;
    }

    public void setSuccessor(Node successor){
        this.successor = successor;
    }

    public Node getSuccessor(){
        return this.successor;
    }
}