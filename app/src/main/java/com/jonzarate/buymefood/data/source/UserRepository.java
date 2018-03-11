package com.jonzarate.buymefood.data.source;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
                .whereEqualTo(User.Field.Nick, nick)
                .whereEqualTo(User.Field.Password, password)
                .get();

        QuerySnapshot query = null;
        try {
            query = Tasks.await(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (query != null) {
            List<User> users = query.toObjects(User.class);

            // There should only be one result
            // Multiple results could be reported if required
            if (users.size() == 1) {
                return users.get(0);
            }
        }

        return null;
    }
}
