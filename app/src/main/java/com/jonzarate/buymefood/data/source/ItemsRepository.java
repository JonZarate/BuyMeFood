package com.jonzarate.buymefood.data.source;

import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class ItemsRepository implements ItemsSource {

    private FirebaseFirestore mFirestore;

    public ItemsRepository (FirebaseFirestore firestore) {
        mFirestore = firestore;
    }


}
