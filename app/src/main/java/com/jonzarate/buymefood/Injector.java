package com.jonzarate.buymefood;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jonzarate.buymefood.data.repo.ItemsRepository;
import com.jonzarate.buymefood.data.repo.ItemsSource;
import com.jonzarate.buymefood.data.repo.UserRepository;
import com.jonzarate.buymefood.data.repo.UserSource;
import com.jonzarate.buymefood.data.source.cache.Cache;
import com.jonzarate.buymefood.data.source.cache.CacheSource;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class Injector {

    private static CacheSource mCacheSource;
    private static ItemsSource mItemsSource;
    private static UserSource mUserSource;

    private static BuyMeFoodViewModelProvider mProvider;


    public static CacheSource getCacheSource() {
        if (mCacheSource == null) {
            mCacheSource = new Cache();
        }

        return mCacheSource;
    }

    public static ItemsSource getItemSource() {
        if (mItemsSource == null) {
            mItemsSource = new ItemsRepository(getFirebaseFirestore());
        }

        return mItemsSource;
    }

    public static UserSource getUserSource() {
        if (mUserSource == null) {
            mUserSource = new UserRepository(getCacheSource(), getFirebaseFirestore());
        }

        return mUserSource;
    }

    private static FirebaseFirestore getFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    public static BuyMeFoodViewModelProvider getViewModerlProvider() {
        if (mProvider == null) {
            mProvider = new BuyMeFoodViewModelProvider(getUserSource());
        }
        return mProvider;
    }
}
