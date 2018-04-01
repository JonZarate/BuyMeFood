package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.usecase.GetGroupInteractor;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class ItemListPresenter implements ItemListContract.Presenter, GetGroupInteractor.Callback {

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
    public void onRefreshed() {
        new GetGroupInteractor(this, Injector.getUserSource(), mGroup.getId()).execute();
    }

    private void initializeView () {
        mView.setGroup(mGroup);
        mView.notifyGroupItemsSet();
    }

    @Override
    public void onGetGroupSuccess(Group group) {
        mGroup = group;
        initializeView();
        mView.hideRefreshing();
    }

    @Override
    public void onGetGroupFail() {
        mView.hideRefreshing();
    }
}
