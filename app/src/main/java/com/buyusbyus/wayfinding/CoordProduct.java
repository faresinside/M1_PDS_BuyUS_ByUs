package com.buyusbyus.wayfinding;

import com.google.android.gms.maps.model.LatLng;

public class CoordProduct {

    private LatLng listcoord;
    private String productName;
    private int buy;

    public CoordProduct(LatLng listcoord, String productName, int buy){
        this.listcoord = listcoord;
        this.productName = productName;
        this.buy = buy;
    }

    public LatLng getListcoord() {
        return listcoord;
    }

    public void setListcoord(LatLng listcoord) {
        this.listcoord = listcoord;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

}
