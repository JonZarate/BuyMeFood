package com.jonzarate.buymefood.itemlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.utils.ActivityUtils;

public class ItemListActivity extends AppCompatActivity {

    public static final String EXTRA_GROUP = "extra_group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Group group = (Group) getIntent().getExtras().getSerializable(EXTRA_GROUP);

        ItemListFragment fragment = (ItemListFragment)
                getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = ItemListFragment.newInstance();

            ItemListContract.Presenter presenter = new ItemListPresenter(fragment, group);

            fragment.setPresenter(presenter);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.container);
        }
    }

}
