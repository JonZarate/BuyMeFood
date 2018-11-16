package com.jonzarate.buymefood.data.source;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.utils.FirestoreUtils;

/**
 * Created by JonZarate on 11/03/2018.
 */

public class UserRepository implements UserSource {

    private FirebaseFirestore mFirestore;

    public UserRepository(FirebaseFirestore firestore)
    {
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
            return FirestoreUtils.toObject(query, Group.class);
        }

        return null;
    }

    @Override
    public Group getGroup(String groupId) {
        Task<DocumentSnapshot> task = mFirestore.collection(Group.COLLECTION)
                .document(groupId)
                .get();

        DocumentSnapshot document = null;
        try {
            document = Tasks.await(task);
        } catch (Exception ignore) {}

        if (document != null){
            return document.toObject(Group.class).withId(document.getId());
        }

        return null;
    }
}
