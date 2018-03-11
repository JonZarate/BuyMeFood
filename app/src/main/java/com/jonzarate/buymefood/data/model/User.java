package com.jonzarate.buymefood.data.model;

import java.io.Serializable;

/**
 * Created by JonZarate on 27/02/2018.
 */

public class User implements Serializable {

    public static String FIRESTORE_COLLECTION = "users";

    public interface Field {
        String Nick = "nick";
        String Password = "pass";
    }

    private String nick;
    private String pass;

    public String getNick() {
        return nick;
    }

    public String getPass() {
        return pass;
    }

}
