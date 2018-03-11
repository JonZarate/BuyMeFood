package com.jonzarate.buymefood.itemlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.User;
import com.jonzarate.buymefood.utils.ActivityUtils;

public class ItemListActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        User user = (User) getIntent().getExtras().getSerializable(EXTRA_USER);

        ItemListFragment fragment = (ItemListFragment)
                getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = ItemListFragment.newInstance();

            ItemListContract.Presenter presenter = new ItemListPresenter(fragment, user);

            fragment.setPresenter(presenter);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.container);
        }
    }

}
