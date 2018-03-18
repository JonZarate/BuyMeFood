package com.jonzarate.buymefood.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JonZarate on 27/02/2018.
 */

public class User extends BaseModel implements Serializable {

    public static String FIRESTORE_COLLECTION = "users";

    public interface Field {
        String NICK = "nick";
        String PASSWORD = "pass";
        String MY_ITEMS = "myItems";
    }

    private String nick;
    private String pass;
    private List<String> myItems;

    public void setMyItems(List<String> myItems) {
        this.myItems = myItems;
    }

    public List<String> getMyItems() {
        return myItems;
    }

    public String getNick() {
        return nick;
    }

    public String getPass() {
        return pass;
    }

}
