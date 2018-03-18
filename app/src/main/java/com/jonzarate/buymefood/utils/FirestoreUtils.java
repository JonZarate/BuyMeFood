package com.jonzarate.buymefood.utils;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.BaseModel;
import com.jonzarate.buymefood.data.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonZarate on 15/03/2018.
 */

public class FirestoreUtils {


    public static <T extends BaseModel> List<T> toObjects(QuerySnapshot query, Class<T> modelClass) {
        List<DocumentSnapshot> snapshots = query.getDocuments();

        ArrayList<T> array = new ArrayList<>();
        for (DocumentSnapshot document : snapshots) {
            T item = document.toObject(modelClass).withId(document.getId());
            array.add(item);
        }

        return array;
    }

    public static <T extends BaseModel> T toObject(QuerySnapshot query, Class<T> modelClass) {
        List<T> array = toObjects(query, modelClass);

        // There should only be one result
        if (array.size() == 1) {
            return array.get(0);
        }
        return null;
    }

}
