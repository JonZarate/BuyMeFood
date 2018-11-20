package com.jonzarate.buymefood.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jonzarate.buymefood.arch.BuyMeFoodViewModelProvider;
import com.jonzarate.buymefood.arch.Event;
import com.jonzarate.buymefood.arch.Injector;
import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.itemlist.ItemListActivity;
import com.jonzarate.buymefood.utils.ActivityUtils;
import com.jonzarate.buymefood.utils.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.login_layout)
    ConstraintLayout mLayout;

    @BindView(R.id.login_progressbar)
    ProgressBar mProgress;

    @BindView(R.id.login_nick)
    TextView mNick;

    @BindView(R.id.login_pass)
    TextView mPass;

    @BindView(R.id.login_button)
    Button mButton;

    private LoginViewModel mViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);

        ActivityUtils.initToolbar((AppCompatActivity) this.getActivity(), R.id.toolbar, true);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();

        initLogin();

        initNavigation();
    }

    private void initViewModel() {
        BuyMeFoodViewModelProvider provider = Injector.getViewModerlProvider();
        mViewModel = ViewModelProviders.of(this, provider).get(LoginViewModel.class);
    }

    private void initLogin() {
        mViewModel.loginEvent.observe(this, new Observer<Event<LoginEvent>>() {
            @Override
            public void onChanged(Event<LoginEvent> loginEventEvent) {
                switch (loginEventEvent.peekContent()) {
                    case LOGIN_SUCCESS:
                        startItemListActivity();
                        break;

                    case LOGIN_FAILED:
                        erasePassword();
                        hideLoading();
                        showWrongCredentialsError();
                        enableLoginButton();
                        break;
                }
            }
        });
    }

    private void initNavigation() {

    }

    @OnClick(R.id.login_button)
    public void onClick(View view){
        mViewModel.login(mNick.getText().toString(), mPass.getText().toString());
        disableLoginButton();
        showLoading();
        hideKeyboard();
    }


    private void startItemListActivity() {
        Intent intent = new Intent(getContext(), ItemListActivity.class);
        startActivity(intent);

        getActivity().finish();
    }

    private void erasePassword() {
        mPass.setText("");
    }

    private void showLoading() {
        setLoadingVisibilityAnimation(View.VISIBLE);
    }

    private void hideLoading() {
        setLoadingVisibilityAnimation(View.GONE);
    }

    private void setLoadingVisibilityAnimation(int visibility){
        AnimationUtils.setViewVisibility(mLayout, R.id.login_progressbar_layout, visibility);
    }

    private void hideKeyboard() {
        ActivityUtils.hideKeyboard(getContext(), getActivity().getCurrentFocus());
    }

    private void disableLoginButton() {
        mButton.setEnabled(false);
    }

    private void enableLoginButton() {
        mButton.setEnabled(true);
    }

    private void showWrongCredentialsError() {
        Toast.makeText(getContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
    }
}
