package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.data.model.Group;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View mView;
    private Group mGroup;

    private boolean mIsInitialized;

    public ItemListPresenter (ItemListContract.View view, Group group) {
        mView = view;
        mGroup = group;
        mIsInitialized = false;
    }

    @Override
    public void start() {
        if (!mIsInitialized){
            mIsInitialized = true;
            initializeView();
        }
    }

    @Override
    public boolean isInitialized() {
        return mIsInitialized;
    }

    @Override
    public void onRefreshed() {

    }

    private void initializeView () {
        mView.setGroup(mGroup);
        mView.notifyGroupItemsSet();
    }

}
