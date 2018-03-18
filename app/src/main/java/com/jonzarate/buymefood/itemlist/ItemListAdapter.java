package com.jonzarate.buymefood.itemlist;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.GroupItems;
import com.jonzarate.buymefood.data.model.Item;
import com.jonzarate.buymefood.data.model.UserItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JonZarate on 27/02/2018.
 */

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    private Resources mResources;
    private GroupItems mGroupItems;
    private List<Object> mList;

    public ItemListAdapter (Resources resources) {
        mResources = resources;
    }

    public void setGroup(GroupItems group) {
        mGroupItems = group;
        mList = new ArrayList<>();

        for (UserItems userItems : mGroupItems.getUserItems()){
            mList.add(userItems.getUser());
            mList.addAll(userItems.getItems());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_HEADER:
                holder = new HeaderViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.viewholder_item_header, parent, false));
                break;
            case TYPE_ITEM:
                holder = new ItemViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.viewholder_item, parent, false));
                break;
        }

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        Object item = mList.get(position);
        if (item instanceof String){
            type = TYPE_HEADER;
        }
        return type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
            bindHeader((HeaderViewHolder) holder, (String) mList.get(position));
        } else if (holder instanceof ItemViewHolder){
            bindItem((ItemViewHolder) holder, (Item) mList.get(position));
        }
    }

    private void bindHeader(HeaderViewHolder holder, String nick){
        holder.nick.setText(nick);
    }

    private void bindItem(ItemViewHolder holder, final Item item){
        holder.active.setChecked(item.isActive());
        holder.amount.setText(
                String.format(mResources.getString(R.string.placeholder_amount), item.getAmount()));
        holder.product.setText(item.getProduct());
        holder.user.setText(item.getPoster());

        holder.active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setActive(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nick)
        TextView nick;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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
