package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.BasePresenter;
import com.jonzarate.buymefood.BaseView;
import com.jonzarate.buymefood.data.model.Item;

import java.util.List;

/**
 * Created by JonZarate on 26/02/2018.
 */

public interface ItemListContract {

    interface Presenter extends BasePresenter {

        void onRefreshed();

    }

    interface View extends BaseView<ItemListContract.Presenter> {

        void setItems(List<Item> items);

        void showRefreshing();

        void hideRefreshing();
    }

}
