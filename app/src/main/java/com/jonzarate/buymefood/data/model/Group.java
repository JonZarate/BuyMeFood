package com.jonzarate.buymefood.data.model;

/**
 * Created by JonZarate on 08/03/2018.
 */

public class Group {

    public static String FIRESTORE_COLLECTION = "groups";

    private User[] users;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
