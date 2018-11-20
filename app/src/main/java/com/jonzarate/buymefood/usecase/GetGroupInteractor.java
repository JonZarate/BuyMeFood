package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.arch.BaseInteractor;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.repo.UserSource;

/**
 * Created by JonZarate on 13/03/2018.
 */

public class GetGroupInteractor extends BaseInteractor {

    public interface Callback {
        void onGetGroupSuccess(Group group);
        void onGetGroupFail();
    }

    private Callback mCallback;
    private UserSource mSource;
    private Group mGroup;

    public GetGroupInteractor(Callback callback, UserSource userSource) {
        mCallback = callback;
        mSource = userSource;
    }

    @Override
    protected void executeWorkerThread() {
        mGroup = mSource.refreshGroup(mSource.getCacheGroup().getId());
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
