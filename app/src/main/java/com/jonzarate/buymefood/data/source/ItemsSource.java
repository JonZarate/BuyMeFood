package com.jonzarate.buymefood.data.source;

import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.GroupItems;
import com.jonzarate.buymefood.data.model.Item;

import java.util.List;

/**
 * Created by JonZarate on 26/02/2018.
 */

public interface ItemsSource {

    GroupItems getItems(Group group);

}
