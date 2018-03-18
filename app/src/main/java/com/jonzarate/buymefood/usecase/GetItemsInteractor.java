package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.GroupItems;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.source.ItemsSource;

import java.util.List;

/**
 * Created by JonZarate on 28/02/2018.
 */

public class GetItemsInteractor extends BaseInteractor {

    public interface Callback {
        void onItemsDownloaded(GroupItems groupItems);
        void onItemsNotDownloaded();
    }

    private Callback mCallback;
    private ItemsSource mSource;
    private Group mGroup;
    private GroupItems mGroupItems;

    public GetItemsInteractor(Callback callback, ItemsSource source, Group group) {
        mCallback = callback;
        mSource = source;
        mGroup = group;
    }


    @Override
    protected void executeWorkerThread() {
        mGroupItems = mSource.getItems(mGroup);
    }

    @Override
    protected void executeMainThread() {
        if (mGroupItems != null) {
            mCallback.onItemsDownloaded(mGroupItems);
        } else {
            mCallback.onItemsNotDownloaded();
        }
    }


}
