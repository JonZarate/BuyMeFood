package com.jonzarate.buymefood.data.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JonZarate on 08/03/2018.
 */

public class Group extends BaseModel implements Serializable {

    public static String COLLECTION = "groups";

    public interface Field {
        String NAME = "name";
        String CURRENCY = "currency";
        String AUTHS = "auths";
        String NICKS = "nicks";
        String ITEMS = "items";
        String HISTORY = "history";
        String DEBTS = "debts";
        String SHARED_ITEMS = "sharedItems";
    }

    private String name;
    private String currency;
    private Map<String, String> auths;
    private Map<String, String> nicks;
    private Map<String, List<Item>> items;
    private Map<String, List<Item>> history;
    private Map<String, Map<String, List<Item>>> debts;
    private List<Item> sharedItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, String> getAuths() {
        return auths;
    }

    public void setAuths(Map<String, String> auths) {
        this.auths = auths;
    }

    public Map<String, String> getNicks() {
        return nicks;
    }

    public void setNicks(Map<String, String> nicks) {
        this.nicks = nicks;
    }

    public Map<String, List<Item>> getItems() {
        return items;
    }

    public void setItems(Map<String, List<Item>> items) {
        this.items = items;
    }

    public Map<String, List<Item>> getHistory() {
        return history;
    }

    public void setHistory(Map<String, List<Item>> history) {
        this.history = history;
    }

    public Map<String, Map<String, List<Item>>> getDebts() {
        return debts;
    }

    public void setDebts(Map<String, Map<String, List<Item>>> debts) {
        this.debts = debts;
    }

    public List<Item> getSharedItems() {
        return sharedItems;
    }

    public void setSharedItems(List<Item> sharedItems) {
        this.sharedItems = sharedItems;
    }
}
