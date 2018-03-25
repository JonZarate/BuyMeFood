package com.jonzarate.buymefood.login;

import com.jonzarate.buymefood.BasePresenter;
import com.jonzarate.buymefood.BaseView;
import com.jonzarate.buymefood.data.model.Group;

/**
 * Created by JonZarate on 11/03/2018.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void onLoginClicked(String nick, String pass);

        void onJoinClicked(String groupId);
    }

    interface View extends BaseView<Presenter> {

        void openItemList(Group group);

        void erasePassword();

        void showLoading();

        void hideLoading();

        void hideKeyboard();

        void disableLoginButton();

        void enableLoginButton();

        void showWrongCredentialsError();

        void showGroupDialog();

        void dismissGroupDialog();

        void enableJoinButton();

        void disableJoinButton();

    }

}
