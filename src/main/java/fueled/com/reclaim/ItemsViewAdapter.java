package fueled.com.reclaim;

import android.content.Context;
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

    final private ItemViewTypeAdapter itemViewTypeAdapter;
    private Context context;
    private List<BaseItem> items;

    public ItemsViewAdapter(Context context, ItemViewTypeAdapter itemViewTypeAdapter) {
        this(context, itemViewTypeAdapter, new ArrayList<BaseItem>());
    }

    public ItemsViewAdapter(Context context, ItemViewTypeAdapter itemViewTypeAdapter, List<BaseItem> items) {
        this.itemViewTypeAdapter = itemViewTypeAdapter;
        this.context = context;
        this.items = items;
    }

    /**
     * Add multiple items to the adapter.
     *
     * @param items the list of items to be added
     */
    public void addItemsList(List<? extends BaseItem> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Add multiple items to the adapter at the specified position
     * and shifts items that are currently at this position to the right.
     *
     * @param position the position at which to add the first item in the list
     * @param items    the list of item to be added
     */
    public void additemsList(int position, List<? extends BaseItem> items) {
        this.items.addAll(position, items);
        notifyDataSetChanged();
    }

    /**
     * Add item to the adapter.
     *
     * @param item the item to be added
     */
    public void addItem(BaseItem item) {
        additem(items.size(), item);
    }

    /**
     * Add item to the adapter at the specified position.
     *
     * @param position the position at which to add the item in the list
     * @param item     the item to be added
     */
    public void additem(int position, BaseItem item) {
        item.setPositionInAdapter(position);
        items.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * Removes the item at the specified position in this adapter view.
     *
     * @param position the position of the item to be removed
     */
    public void removeItemAt(int position) {
        items.remove(position);
        updateitemsPosition();
        notifyItemRemoved(position);
    }

    private void updateitemsPosition() {
        for (int i = 0; i < items.size(); i++) {
            BaseItem item = items.get(i);
            item.setPositionInAdapter(i);
        }
    }

    /**
     * Removes all items inside the recycler adapter view.
     */
    public void clearAllRecyclerItems() {
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * Returns the item at the specified position in the adapter.
     *
     * @param position position of the item to be returned
     * @return the item at the specified position in the adapter
     */
    public BaseItem getitem(int position) {
        return items.get(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Class<? extends BaseItem> itemClass = getType(viewType);

        if (itemClass == null || itemClass == BaseItem.class) {
            return new EmptyViewHolder(viewGroup);
        }

        for (BaseItem item : items) {
            if (item.getClass().isAssignableFrom(itemClass)) {
                View view = LayoutInflater.from(context).inflate(item.layoutId, viewGroup, false);

                return item.onCreateViewHolder(view);
            }
        }

        return null;
    }

    private Class<? extends BaseItem> getType(int viewType) {
        if (itemViewTypeAdapter != null) {
            return ((ItemType) itemViewTypeAdapter.getType(viewType)).getItemClass();
        } else {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        items.get(position).onBindViewHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType().getValue();
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
