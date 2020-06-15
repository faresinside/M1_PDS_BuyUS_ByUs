package com.buyusbyus.Model;

public class Rules {


    private String itemA;
    private String itemB;


    public Rules(String itemA, String itemB) {

        this.itemA = itemA;
        this.itemB = itemB;

    }

    public Rules() {



    }


    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public String getItemB() {
        return itemB;
    }
}
