package com.jonzarate.buymefood.data.source;

import com.jonzarate.buymefood.data.model.Item;

import java.util.List;

/**
 * Created by JonZarate on 26/02/2018.
 */

public interface ItemsSource {

    List<Item> getItems();

}
