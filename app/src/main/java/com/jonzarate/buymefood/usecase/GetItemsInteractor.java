package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.source.ItemsSource;

import java.util.List;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class GetItemsInteractor extends BaseInteractor {

    public interface Callback {
        void onItemsDownloaded(List<Item> items);
        void onItemsNotDownloaded();
    }

    private Callback mCallback;
    private ItemsSource mSource;
    private List<Item> mItems;

    public GetItemsInteractor(Callback callback, ItemsSource source) {
        mCallback = callback;
        mSource = source;
    }


    @Override
    protected void executeWorkerThread() {
        mItems = mSource.getItems();
    }

    @Override
    protected void executeMainThread() {
        if (mItems != null) {
            mCallback.onItemsDownloaded(mItems);
        } else {
            mCallback.onItemsNotDownloaded();
        }
    }


}
