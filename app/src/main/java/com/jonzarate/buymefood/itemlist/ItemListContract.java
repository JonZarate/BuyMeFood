package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.BasePresenter;
import com.jonzarate.buymefood.BaseView;
import com.jonzarate.buymefood.data.model.Group;

/**
 * Created by JonZarate on 26/02/2018.
 */

public interface ItemListContract {

    interface Presenter extends BasePresenter {

        void onRefreshed();

    }

    interface View extends BaseView<ItemListContract.Presenter> {

        void setGroup(Group group );

        void notifyGroupItemsSet();

        void showRefreshing();

        void hideRefreshing();
    }

}
