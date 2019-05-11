package com.jonzarate.buymefood.itemlist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.utils.ActivityUtils;

public class ItemListActivity extends AppCompatActivity {

    public static final String EXTRA_GROUP = "extra_group";
    public static final String EXTRA_GROUP_ID = "extra_group_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Group group = (Group) getIntent().getExtras().getSerializable(EXTRA_GROUP);
        if (group != null){
            group.withId(getIntent().getStringExtra(EXTRA_GROUP_ID));
        }

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
