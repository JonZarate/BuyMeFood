package com.jonzarate.buymefood.itemlist;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.usecase.GetGroupInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class ItemListPresenter implements ItemListContract.Presenter, GetGroupInteractor.Callback {

    private ItemListContract.View mView;
    private Group mGroup;

    private boolean mIsInitialized;

    private List<Item> mCheckedItems;

    public ItemListPresenter (ItemListContract.View view, Group group) {
        mView = view;
        mGroup = group;
        mIsInitialized = false;
        mCheckedItems = new ArrayList<>();
    }

    @Override
    public void start() {
        if (!mIsInitialized){
            mIsInitialized = true;
            initializeView();
        } else {
            setFabIcon();
        }
    }

    @Override
    public void onRefreshed() {
        new GetGroupInteractor(this, Injector.getUserSource(), mGroup.getId()).execute();
    }

    @Override
    public void onFabClicked() {

    }

    @Override
    public void onItemChecked(Item item) {
        if (mCheckedItems.size() == 0) {
            mView.setCheckFabIcon();
        }

        mCheckedItems.add(item);
    }

    @Override
    public void onItemUnchecked(Item item) {
        mCheckedItems.remove(item);

        if (mCheckedItems.size() == 0) {
            mView.setAddFabIcon();
        }
    }

    private void initializeView () {
        mView.setGroup(mGroup);
        mView.notifyGroupItemsSet();
    }

    private void setFabIcon() {
        if (mCheckedItems.size() == 0) {
            mView.setAddFabIcon();
        } else {
            mView.setCheckFabIcon();
        }
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
