package com.jonzarate.buymefood.data.source;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.GroupItems;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.model.UserItems;
import com.jonzarate.buymefood.utils.FirestoreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class ItemsRepository implements ItemsSource {

    private FirebaseFirestore mFirestore;

    public ItemsRepository (FirebaseFirestore firestore) {
        mFirestore = firestore;
    }

    @Override
    public GroupItems getItems(Group group) {

        QuerySnapshot query = null;
        try {
            query = Tasks.await(mFirestore.collection(Item.FIRESTORE_COLLECTION).get());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

        if (query != null) {
            List<Item> items = FirestoreUtils.toObjects(query, Item.class);
            List<UserItems> userItemsList = new ArrayList<>();

            Map<String, List<Item>> map = new HashMap<>();
            for (Item item : items) {
                List<Item> list;
                if (map.containsKey(item.getPoster())){
                    list = map.get(item.getPoster());
                } else {
                    list = new ArrayList<>();
                    map.put(item.getPoster(), list);
                }
                list.add(item);
            }

            for (String key : map.keySet()) {
                userItemsList.add(new UserItems(key, map.get(key)));
            }

            GroupItems groupItems = new GroupItems(userItemsList);
            return groupItems;
        }

        return null;
    }
}
