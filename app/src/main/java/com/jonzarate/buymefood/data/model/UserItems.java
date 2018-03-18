package com.jonzarate.buymefood.data.model;

import java.util.List;

/**
 * Created by JonZarate on 15/03/2018.
 */

public class UserItems {

    private String mUser;
    private List<Item> mItems;

    public UserItems (String user, List<Item> items){
        mUser = user;
        mItems = items;
    }

    public String getUser() {
        return mUser;
    }

    public List<Item> getItems() {
        return mItems;
    }
}
