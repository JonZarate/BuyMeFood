package com.jonzarate.buymefood.data.source;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class ItemsRepository implements ItemsSource {

    private FirebaseFirestore mFirestore;

    public ItemsRepository (FirebaseFirestore firestore) {
        mFirestore = firestore;
    }

    @Override
    public List<Item> getItems() {

        QuerySnapshot query = null;
        try {
            query = Tasks.await(mFirestore.collection(Item.FIRESTORE_COLLECTION).get());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

        ArrayList<Item> items = new ArrayList<>();

        if (query != null) {
            List<DocumentSnapshot> snapshots = query.getDocuments();

            for (DocumentSnapshot document : snapshots) {
                items.add(document.toObject(Item.class));
            }
        }

        return items;
    }
}
