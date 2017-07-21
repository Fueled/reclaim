package com.fueled.reclaim;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussein@fueled.com on 02/03/2016.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class ItemsViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<BaseItem> items;

    public ItemsViewAdapter(Context context) {
        this(context, new ArrayList<BaseItem>());
    }

    public ItemsViewAdapter(Context context, List<BaseItem> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * Add multiple items to the adapter.
     *
     * @param items the list of items to be added
     */
    public void addItemsList(List<? extends BaseItem> items) {
        int startSize = getItemCount();

        this.items.addAll(items);
        updateItemPositions(startSize);
        notifyItemRangeInserted(startSize, items.size());
    }

    /**
     * Add multiple items to the adapter at the specified position
     * and shifts items that are currently at this position to the right.
     *
     * @param position the position at which to add the first item in the list
     * @param items    the list of item to be added
     */
    public void addItemsList(int position, List<? extends BaseItem> items) {
        this.items.addAll(position, items);
        updateItemPositions(position);
        notifyItemRangeInserted(position, items.size());
    }

    /**
     * Removes all items inside the adapter and add new items from the specified list.
     *
     * @param items the list of item to be added
     */
    public void replaceItems(List<? extends BaseItem> items) {
        this.items.clear();
        this.items.addAll(items);
        updateItemPositions(0);
        notifyDataSetChanged();
    }

    /**
     * Removes all items inside the adapter and add new items from the specified list.
     *
     * @param items        the list of item to be added
     * @param useDiffCheck indicates whether {@link DiffChecker} should be used to only apply needed
     *                     changes.
     */
    public void replaceItems(List<? extends BaseItem> items, boolean useDiffCheck) {
        if (!useDiffCheck) {
            replaceItems(items);
        } else {
            DiffUtil.DiffResult result = DiffChecker.calculateDiff(this.items, items);
            this.items.clear();
            this.items.addAll(items);
            updateItemPositions(0);
            result.dispatchUpdatesTo(this);
        }
    }

    public void replaceItemAt(int position, BaseItem item) {
        items.set(position, item);
        item.setPositionInAdapter(position);
        notifyItemChanged(position);
    }

    /**
     * Add item to the adapter.
     *
     * @param item the item to be added
     */
    public void addItem(BaseItem item) {
        addItem(items.size(), item);
    }

    /**
     * Add item to the adapter at the specified position.
     *
     * @param position the position at which to add the item in the list
     * @param item     the item to be added
     */
    public void addItem(int position, BaseItem item) {
        items.add(position, item);
        updateItemPositions(position);
        notifyItemInserted(position);
    }

    /**
     * Removes the item at the specified position in this adapter view.
     *
     * @param position the position of the item to be removed
     */
    public void removeItemAt(int position) {
        items.remove(position);
        updateItemPositions(position);
        notifyItemRemoved(position);
    }

    /**
     * Removes all items inside the recycler adapter view.
     */
    public void clearAllRecyclerItems() {
        int total = items.size();

        items.clear();

        notifyItemRangeRemoved(0, total);
    }

    private void updateItemPositions(int startPosition) {
        for (int i = startPosition; i < items.size(); i++) {
            items.get(i).setPositionInAdapter(i);
        }
    }

    /**
     * Returns the item at the specified position in the adapter.
     *
     * @param position position of the item to be returned
     * @return the item at the specified position in the adapter
     */
    public BaseItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        for (BaseItem item : items) {
            if (item.getType().ordinal() == viewType) {
                View view = LayoutInflater.from(context).inflate(item.layoutId, viewGroup, false);

                return item.onCreateViewHolder(view);
            }
        }

        return new EmptyViewHolder(viewGroup);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        items.get(position).onBindViewHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType().ordinal();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class EmptyViewHolder extends BaseViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

}