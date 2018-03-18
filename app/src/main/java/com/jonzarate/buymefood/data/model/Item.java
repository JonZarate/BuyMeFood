package com.jonzarate.buymefood.data.model;

import java.io.Serializable;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class Item extends BaseModel implements Serializable {

    public static String FIRESTORE_COLLECTION = "items";

    private boolean active;
    private int amount;
    private float price;
    private String product;
    private String poster;

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

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
