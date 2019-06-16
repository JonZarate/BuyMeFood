package com.jonzarate.buymefood.data.repo;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.source.cache.CacheSource;
import com.jonzarate.buymefood.utils.FirestoreUtils;

/**
 * Created by JonZarate on 11/03/2018.
 */

public class UserRepository implements UserSource {

    private CacheSource mSource;
    private FirebaseFirestore mFirestore;

    public UserRepository(CacheSource source, FirebaseFirestore firestore) {
        mSource = source;
        mFirestore = firestore;
    }


    @Override
    public Group login(String nick, String password) {
        String field = String.format("%s.%s", Group.Field.AUTHS, nick);

        Task<QuerySnapshot> task = mFirestore.collection(Group.COLLECTION)
                .whereEqualTo(field, password)
                .get();

        QuerySnapshot query = null;
        try {
            query = Tasks.await(task);
        } catch (Exception ignore) { }

        if (query != null) {
            Group group = FirestoreUtils.toObject(query, Group.class);
            mSource.setGroup(group);
            return group;
        }

        return null;
    }

    @Override
    public Group refreshGroup(String groupId) {
        Task<DocumentSnapshot> task = mFirestore.collection(Group.COLLECTION)
                .document(groupId)
                .get();

        DocumentSnapshot document = null;
        try {
            document = Tasks.await(task);
        } catch (Exception ignore) {}

        if (document != null){
            Group group = document.toObject(Group.class).withId(document.getId());
            mSource.setGroup(group);
            return group;
        }

        return null;
    }

    @Override
    public Group getCacheGroup() {
        return mSource.getGroup();
    }


    @Override
    public void saveCacheGroup(Group group) {
        mSource.setGroup(group);
    }
}
