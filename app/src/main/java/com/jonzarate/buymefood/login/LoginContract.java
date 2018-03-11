package com.jonzarate.buymefood.login;

import com.jonzarate.buymefood.BasePresenter;
import com.jonzarate.buymefood.BaseView;
import com.jonzarate.buymefood.data.model.User;

/**
 * Created by JonZarate on 11/03/2018.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void onLoginClicked(String nick, String pass);
    }

    interface View extends BaseView<Presenter> {

        void openItemList(User user);

        void erasePassword();

        void showLoading();

        void hideLoading();

        void hideKeyboard();

    }

}
