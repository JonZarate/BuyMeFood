package com.jonzarate.buymefood.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.utils.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            loginFragment.setPresenter(new LoginPresenter(loginFragment));

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), loginFragment, R.id.content_frame);
        }
    }
}
