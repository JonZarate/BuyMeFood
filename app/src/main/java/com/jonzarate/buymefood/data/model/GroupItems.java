package com.jonzarate.buymefood.data.model;

import java.util.List;

/**
 * Created by JonZarate on 15/03/2018.
 */

public class GroupItems {

    private List<UserItems> mUserItems;

    public GroupItems(List<UserItems> userItemsList){
        mUserItems = userItemsList;
    }

    public List<UserItems> getUserItems() {
        return mUserItems;
    }
}
