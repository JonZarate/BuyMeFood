package com.jonzarate.buymefood.data.source;

import com.jonzarate.buymefood.data.model.User;

/**
 * Created by JonZarate on 11/03/2018.
 */



public interface UserSource {

    User login(String nick, String password);

}
