package com.jonzarate.buymefood.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class Item extends BaseModel implements Serializable {

    public interface Field {
        String AMOUNT = "amount";
        String PRODUCT = "product";
        String NOTES = "notes";
        String DATE_POSTED = "datePosted";
        String DATE_BOUGHT = "dateBought";
    }


    private boolean checked;
    private int amount;
    private String product;
    private String notes;
    private Date datePosted;
    private Date dateBought;

    public Item ( ) {
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }
}
