package com.jonzarate.buymefood.usecase;

import com.jonzarate.buymefood.BaseInteractor;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.data.source.UserSource;

/**
 * Created by JonZarate on 11/03/2018.
 */

public class LoginInteractor extends BaseInteractor {

    public interface Callback {
        void onLoginSuccessful(User user);
        void onLoginFailed();
    }

    private Callback mCallback;
    private UserSource mSource;
    private String mNick, mPass;
    private User mUser;

    public LoginInteractor (Callback callback, UserSource source, String nick, String pass){
        mCallback = callback;
        mSource = source;
        mNick = nick;
        mPass = pass;
    }

    @Override
    protected void executeWorkerThread() {
        mUser = mSource.login(mNick, mPass);
    }

    @Override
    protected void executeMainThread() {
        if (mUser != null){
            mCallback.onLoginSuccessful(mUser);
        } else {
            mCallback.onLoginFailed();
        }
    }
}
