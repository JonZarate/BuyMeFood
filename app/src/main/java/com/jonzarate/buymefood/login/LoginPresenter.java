package com.jonzarate.buymefood.login;

import com.jonzarate.buymefood.Injector;
import com.jonzarate.buymefood.data.model.Group;
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
        //mView.hideLoading();
    }

    @Override
    public void onLoginClicked(String nick, String pass) {
        mView.hideKeyboard();
        mView.showLoading();
        mView.disableLoginButton();
        new LoginInteractor(this, Injector.getUserSource(), nick, pass).execute();
    }

    @Override
    public void onJoinClicked(String groupId) {

    }

    @Override
    public void onLoginSuccessful(Group group) {
        mView.openItemList(group);
        mView.hideLoading();
    }

    @Override
    public void onLoginFailed() {
        mView.showWrongCredentialsError();
        mView.erasePassword();
        mView.enableLoginButton();
        mView.hideLoading();
    }

}
