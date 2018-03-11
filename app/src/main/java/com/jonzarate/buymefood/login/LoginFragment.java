package com.jonzarate.buymefood.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.itemlist.ItemListActivity;
import com.jonzarate.buymefood.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @BindView(R.id.login_progressbar)
    ProgressBar mProgress;

    @BindView(R.id.login_nick)
    TextView mNick;

    @BindView(R.id.login_pass)
    TextView mPass;

    @BindView(R.id.login_button)
    Button mButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @OnClick(R.id.login_button)
    public void onClick(View view){
        // No need to check id
        mPresenter.onLoginClicked(mNick.getText().toString(), mPass.getText().toString());
    }

    @Override
    public void openItemList(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ItemListActivity.EXTRA_USER, user);

        Intent intent = new Intent(getContext(), ItemListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

        getActivity().finish();
    }

    @Override
    public void erasePassword() {
        mPass.setText("");
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(getContext(), getActivity().getCurrentFocus());
    }
}
