package com.jonzarate.buymefood.login;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.usecase.LoginInteractor;

/**
 * Created by JonZarate on 11/03/2018.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback {

    private LoginContract.View mView;

    public LoginPresenter (LoginContract.View view) {
        mView = view;
    }


    @Override
    public void start() {
        mView.hideLoading();
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void onLoginClicked(String nick, String pass) {
        mView.showLoading();
        new LoginInteractor(this, Injector.getUserSource(), nick, pass).execute();
    }

    @Override
    public void onLoginSuccessful(User user) {
        mView.openItemList(user);
        mView.hideLoading();
    }

    @Override
    public void onLoginFailed() {
        mView.erasePassword();
        mView.hideLoading();
    }
}
