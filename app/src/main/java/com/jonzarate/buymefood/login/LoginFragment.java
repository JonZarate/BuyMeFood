package com.jonzarate.buymefood.login;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.itemlist.ItemListActivity;
import com.jonzarate.buymefood.utils.ActivityUtils;
import com.jonzarate.buymefood.utils.AnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements LoginContract.View {


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

    private LoginContract.Presenter mPresenter;

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

        ActivityUtils.initToolbar((AppCompatActivity) this.getActivity(), R.id.toolbar, true);

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
    public void openItemList(Group group) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ItemListActivity.EXTRA_GROUP, group);

        Intent intent = new Intent(getContext(), ItemListActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(ItemListActivity.EXTRA_GROUP_ID, group.getId());
        startActivity(intent);

        getActivity().finish();
    }

    @Override
    public void erasePassword() {
        mPass.setText("");
    }

    @Override
    public void showLoading() {
        setLoadingVisibilityAnimation(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        setLoadingVisibilityAnimation(View.GONE);
    }

    private void setLoadingVisibilityAnimation(int visibility){
        AnimationUtils.setViewVisibility(mLayout, R.id.login_progressbar_layout, visibility);
    }

    @Override
    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(getContext(), getActivity().getCurrentFocus());
    }

    @Override
    public void disableLoginButton() {
        mButton.setEnabled(false);
    }

    @Override
    public void enableLoginButton() {
        mButton.setEnabled(true);
    }

    @Override
    public void showWrongCredentialsError() {
        Toast.makeText(getContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGroupDialog() {

    }

    @Override
    public void dismissGroupDialog() {

    }

    @Override
    public void enableJoinButton() {

    }

    @Override
    public void disableJoinButton() {

    }
}
