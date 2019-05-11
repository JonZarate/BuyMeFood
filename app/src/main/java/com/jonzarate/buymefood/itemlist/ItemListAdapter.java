package com.jonzarate.buymefood.itemlist;

import android.content.res.Resources;
import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jonzarate.buymefood.R;
import com.jonzarate.buymefood.data.model.Group;
import com.jonzarate.buymefood.data.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JonZarate on 27/02/2018.
 */

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public interface OnItemSelectionChange {
        void onItemChecked(Item item);
        void onItemUnchecked(Item item);
    }

    private Resources mResources;
    private OnItemSelectionChange mListener;

    private Group mGroup;
    private List<Object> mList;

    public ItemListAdapter (Resources resources, OnItemSelectionChange listener) {
        mResources = resources;
        mListener = listener;
    }

    public void setGroup(Group group) {
        mGroup = group;
        mList = new ArrayList<>();

        for (String nick : mGroup.getNicks().keySet()){
            if (mGroup.getItems().containsKey(nick)) {
                mList.add(mGroup.getNicks().get(nick)); // User name as header
                mList.addAll(mGroup.getItems().get(nick)); // Item list
            }
        }

        List<Item> sharedItems = mGroup.getSharedItems();
        if (sharedItems != null && sharedItems.size() > 0) {
            mList.add(mResources.getString(R.string.header_shared_items));
            mList.addAll(sharedItems);
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

    private void bindItem(final ItemViewHolder holder, final Item item){
        setCheckBox(holder.completed, holder.product, item.isChecked());
        holder.amount.setText(String.format(mResources.getString(R.string.placeholder_amount), item.getAmount()));
        holder.product.setText(item.getProduct());

        if (item.getNotes() != null && item.getNotes().isEmpty()) {
            holder.notes.setVisibility(View.GONE);

            setProductHolderParams(holder.product, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            holder.notes.setText(item.getNotes());
            holder.notes.setVisibility(View.VISIBLE);

           setProductHolderParams(holder.product, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        holder.completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
                setCheckBox(holder.completed, holder.product, isChecked);
                if (mListener != null) {
                    if (isChecked) {
                      mListener.onItemChecked(item);
                    } else {
                        mListener.onItemUnchecked(item);
                    }
                }
            }
        });
    }

    private void setProductHolderParams(TextView view, int param) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = param;
        view.setLayoutParams(params);
    }

    private void setCheckBox(CheckBox checkBox, TextView text, boolean isChecked){
        checkBox.setChecked(isChecked);
        if (isChecked) {
            text.setPaintFlags(
                    text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            text.setPaintFlags(
                    text.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
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

        @BindView(R.id.completed)
        CheckBox completed;

        @BindView(R.id.amount)
        TextView amount;

        @BindView(R.id.product)
        TextView product;

        @BindView(R.id.notes)
        TextView notes;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
