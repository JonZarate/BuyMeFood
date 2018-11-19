package com.jonzarate.buymefood.itemlist;

import android.os.Bundle;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.utils.ActivityUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ItemListFragment fragment = ItemListFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container);
    }

}
