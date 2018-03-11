package com.jonzarate.buymefood.itemlist;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by JonZarate on 27/02/2018.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

    private Resources mResources;
    private List<Item> mItems;

    public ItemListAdapter (Resources resources) {
        mResources = resources;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.active.setChecked(item.isActive());
        holder.amount.setText(
                String.format(mResources.getString(R.string.placeholder_amount), item.getAmount()));
        holder.product.setText(item.getProduct());
        holder.user.setText(item.getReporter());

        holder.active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setActive(isChecked);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.active)
        CheckBox active;

        @BindView(R.id.amount)
        TextView amount;

        @BindView(R.id.product)
        TextView product;

        @BindView(R.id.user)
        TextView user;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
