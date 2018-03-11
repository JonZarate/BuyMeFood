package com.jonzarate.buymefood.data.model;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class Item {

    public static String FIRESTORE_COLLECTION = "items";

    private boolean active;
    private int amount;
    private float price;
    private String product;
    private String reporter;

    public Item ( ) {
    }

    public boolean isActive() {
        return active;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    public String getReporter() {
        return reporter;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
