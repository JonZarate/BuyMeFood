package com.jonzarate.buymefood.data.repo;

import com.jonzarate.buymefood.data.model.Group;

/**
 * Created by JonZarate on 11/03/2018.
 */



public interface UserSource {

    Group login(String nick, String password);


    Group refreshGroup(String groupId);

    Group getCacheGroup();

    void saveCacheGroup(Group group);

}
