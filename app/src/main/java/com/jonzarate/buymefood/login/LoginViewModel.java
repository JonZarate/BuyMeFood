package com.jonzarate.buymefood.login;

import com.jonzarate.buymefood.arch.Event;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.repo.UserSource;
import com.jonzarate.buymefood.usecase.LoginInteractor;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel implements LoginInteractor.Callback {

    private UserSource repository;

    private Group group;

    public MutableLiveData<Event<LoginEvent>> loginEvent;

    public LoginViewModel (UserSource repository) {
        this.repository = repository;
        this.loginEvent = new MutableLiveData<>();
    }

    public void login(String user, String password) {
        new LoginInteractor(this, repository, user, password).execute();
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public void onLoginSuccessful(Group group) {
        this.group = group;
        notifyLogin(LoginEvent.LOGIN_SUCCESS);
    }

    @Override
    public void onLoginFailed() {
        notifyLogin(LoginEvent.LOGIN_FAILED);
    }

    private void notifyLogin(LoginEvent event) {
        loginEvent.setValue(new Event<>(event));
    }
}
