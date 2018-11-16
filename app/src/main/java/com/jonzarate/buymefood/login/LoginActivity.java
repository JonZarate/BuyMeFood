package com.jonzarate.buymefood.login;

import android.os.Bundle;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.utils.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = LoginFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();

        ActivityUtils.addFragmentToActivity(fragmentManager, loginFragment, R.id.content_frame);
    }
}
