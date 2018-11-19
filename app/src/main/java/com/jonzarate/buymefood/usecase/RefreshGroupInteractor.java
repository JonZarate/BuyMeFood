package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.repo.UserSource;

/**
 * Created by JonZarate on 13/03/2018.
 */

public class RefreshGroupInteractor extends BaseInteractor {

    public interface Callback {
        void onGetGroupSuccess(Group group);
        void onGetGroupFail();
    }

    private Callback mCallback;
    private UserSource mSource;
    private String mGroupId;
    private Group mGroup;

    public RefreshGroupInteractor(Callback callback, UserSource userSource, String groupId) {
        mCallback = callback;
        mSource = userSource;
        mGroupId = groupId;
    }

    @Override
    protected void executeWorkerThread() {
        mGroup = mSource.refreshGroup(mGroupId);
    }

    @Override
    protected void executeMainThread() {
        if (mGroup == null) {
            mCallback.onGetGroupFail();
        } else {
            mCallback.onGetGroupSuccess(mGroup);
        }
    }
}
