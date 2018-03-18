package com.jonzarate.buymefood.data.source;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.utils.FirestoreUtils;

import java.util.HashMap;
import java.util.List;

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
    public User login(String nick, String password) {
        Task<QuerySnapshot> task = mFirestore.collection(User.FIRESTORE_COLLECTION)
                .whereEqualTo(User.Field.NICK, nick)
                .whereEqualTo(User.Field.PASSWORD, password)
                .get();

        QuerySnapshot query = null;
        try {
            query = Tasks.await(task);
        } catch (Exception ignore) { }

        if (query != null) {
            return FirestoreUtils.toObject(query, User.class);
        }

        return null;
    }

    @Override
    public Group getGroup(String nick) {
        String field = String.format("%s.%s", Group.Field.NICKS, nick);

        Task<QuerySnapshot> task = mFirestore.collection(Group.FIRESTORE_COLLECTION)
                .whereEqualTo(field, true)
                .get();

        QuerySnapshot query = null;
        try {
            query = Tasks.await(task);
        } catch (Exception ignore) { }

        if (query != null) {
            Group group = FirestoreUtils.toObject(query, Group.class);
            return group;
        }

        return null;
    }

    @Override
    public void joinGroup(String groupId, String nick) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Group.Field.NICKS, nick);

        Task<Void> task = mFirestore.collection(Group.FIRESTORE_COLLECTION)
                .document(groupId)
                .set(map, SetOptions.merge());

        try {
            Tasks.await(task);
        } catch (Exception ignore) { }
    }
}
