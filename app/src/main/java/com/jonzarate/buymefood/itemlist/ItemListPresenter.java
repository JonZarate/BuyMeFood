package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.usecase.GetItemsInteractor;

import java.util.List;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class ItemListPresenter implements ItemListContract.Presenter, GetItemsInteractor.Callback {

    private ItemListContract.View mView;
    private List<Item> mItems;
    private User mUser;

    private boolean mIsInitialized;

    public ItemListPresenter (ItemListContract.View view, User user) {
        mView = view;
        mUser = user;
        mIsInitialized = false;
    }

    @Override
    public void start() {
        mIsInitialized = true;
        mView.showRefreshing();
        downloadItems();
    }

    @Override
    public boolean isInitialized() {
        return mIsInitialized;
    }

    private void downloadItems() {
        new GetItemsInteractor(this, Injector.getItemSource()).execute();
    }

    @Override
    public void onItemsDownloaded(List<Item> items) {
        mItems = items;
        mView.setItems(mItems);
        mView.hideRefreshing();
    }

    @Override
    public void onItemsNotDownloaded() {

    }

    @Override
    public void onRefreshed() {
        downloadItems();
    }
}
