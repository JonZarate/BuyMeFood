package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.GroupItems;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.usecase.GetItemsInteractor;

import java.util.List;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class ItemListPresenter implements ItemListContract.Presenter, GetItemsInteractor.Callback {

    private ItemListContract.View mView;
    private GroupItems mGroupItems;
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
            downloadItems();
        } else {
            if (mGroupItems != null){
                mView.setGroup(mGroupItems);
            }
        }
    }

    @Override
    public boolean isInitialized() {
        return mIsInitialized;
    }

    private void downloadItems() {
        new GetItemsInteractor(this, Injector.getItemSource(), mGroup).execute();
    }

    @Override
    public void onRefreshed() {
        downloadItems();
    }

    @Override
    public void onItemsDownloaded(GroupItems groupItems) {
        mGroupItems = groupItems;
        mView.setGroup(mGroupItems);
        mView.notifyGroupItemsSet();
    }

    @Override
    public void onItemsNotDownloaded() {

    }
}
