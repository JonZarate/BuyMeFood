package com.jonzarate.buymefood.itemlist;


import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonzarate.buymefood.arch.BuyMeFoodViewModelProvider;
import com.jonzarate.buymefood.arch.Injector;
import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.Item;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ItemListAdapter.OnItemSelectionChange {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeLayout;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private ItemListViewModel mViewModel;
    private ItemListAdapter mAdapter;

    public ItemListFragment() {
        // Required empty public constructor
    }

    public static ItemListFragment newInstance () {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_item_list, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();

        initComponents();

        initObservation();
    }

    private void initViewModel () {
        BuyMeFoodViewModelProvider provider = Injector.getViewModerlProvider();
        mViewModel = ViewModelProviders.of(this, provider).get(ItemListViewModel.class);
    }

    private void initComponents() {
        mAdapter = new ItemListAdapter(getContext().getResources(), this);

        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // Add padding at the bottom
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                    float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 96,
                            getContext().getResources().getDisplayMetrics());
                    outRect.bottom = (int) px;
                }
            }
        });


        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.secondaryColor));
    }

    private void initObservation() {
        mViewModel.getGroup().observe(this, new Observer<Group>() {
            @Override
            public void onChanged(Group group) {
                setGroup(group);
            }
        });
    }

    public void setGroup(Group group) {
        mAdapter.setGroup(group);
        mAdapter.notifyDataSetChanged();
    }

    public void setCheckFabIcon() {
        mFab.setImageResource(R.drawable.ic_check);
    }

    public void setAddFabIcon() {
        mFab.setImageResource(R.drawable.ic_add);
    }

    public void showRefreshing() {
        mSwipeLayout.setRefreshing(true);
    }

    public void hideRefreshing() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mViewModel.refresh();
    }

    @OnClick(R.id.fab)
    public void onClick(View v) {
        mViewModel.create();
    }

    @Override
    public void onItemChecked(Item item) {
    }

    @Override
    public void onItemUnchecked(Item item) {
    }
}
