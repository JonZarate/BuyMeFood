package com.jonzarate.buymefood.data.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JonZarate on 08/03/2018.
 */

public class Group extends BaseModel implements Serializable {

    public static String FIRESTORE_COLLECTION = "groups";

    public interface Field {
        String NICKS = "nicks";
    }

    private Map<String, Boolean> nicks;

    public Map<String, Boolean> getNicks() {
        return nicks;
    }

    public void setNicks(Map<String, Boolean> nicks) {
        this.nicks = nicks;
    }
}
