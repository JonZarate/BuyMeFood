package com.jonzarate.buymefood;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jonzarate.buymefood.data.source.ItemsRepository;
import com.jonzarate.buymefood.data.source.ItemsSource;
import com.jonzarate.buymefood.data.source.UserRepository;
import com.jonzarate.buymefood.data.source.UserSource;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class Injector {

    private static ItemsSource mItemsSource;
    private static UserSource mUserSource;

    public static ItemsSource getItemSource() {
        if (mItemsSource == null) {
            mItemsSource = new ItemsRepository(getFirebaseFirestore());
        }

        return mItemsSource;
    }

    public static UserSource getUserSource() {
        if (mUserSource == null) {
            mUserSource = new UserRepository(getFirebaseFirestore());
        }

        return mUserSource;
    }

    public static FirebaseFirestore getFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

}
