package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.source.UserSource;

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
    private String mNick;
    private Group mGroup;

    public GetGroupInteractor(Callback callback, UserSource userSource, String nick) {
        mCallback = callback;
        mSource = userSource;
        mNick = nick;
    }

    @Override
    protected void executeWorkerThread() {
        mGroup = mSource.getGroup(mNick);
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
