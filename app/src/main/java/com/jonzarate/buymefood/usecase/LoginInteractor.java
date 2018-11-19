package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.repo.UserSource;

/**
 * Created by JonZarate on 11/03/2018.
 */

public class LoginInteractor extends BaseInteractor {

    public interface Callback {
        void onLoginSuccessful(Group group);
        void onLoginFailed();
    }

    private Callback mCallback;
    private UserSource mSource;
    private String mNick, mPass;
    private Group mGroup;

    public LoginInteractor (Callback callback, UserSource source, String nick, String pass){
        mCallback = callback;
        mSource = source;
        mNick = nick;
        mPass = pass;
    }

    @Override
    protected void executeWorkerThread() {
        mGroup = mSource.login(mNick, mPass);
    }

    @Override
    protected void executeMainThread() {
        if (mGroup != null){

            mCallback.onLoginSuccessful(mGroup);
        } else {
            mCallback.onLoginFailed();
        }
    }
}
