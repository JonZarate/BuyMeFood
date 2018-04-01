package com.jonzarate.buymefood.itemlist;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements ItemListContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeLayout;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private ItemListContract.Presenter mPresenter;
    private ItemListAdapter mAdapter;

    public ItemListFragment() {
        // Required empty public constructor
    }

    public static ItemListFragment newInstance () {
        ItemListFragment fragment = new ItemListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mAdapter = new ItemListAdapter(getContext().getResources());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_item_list, container, false);

        ButterKnife.bind(this, root);

        mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
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
        mRecycler.setAdapter(mAdapter);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.secondaryColor));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void setPresenter(ItemListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setGroup(Group group) {
        mAdapter.setGroup(group);
    }

    @Override
    public void notifyGroupItemsSet() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRefreshing() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefreshed();
    }
}
