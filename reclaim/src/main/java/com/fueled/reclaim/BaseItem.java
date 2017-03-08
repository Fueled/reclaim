package com.fueled.reclaim;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by hussein@fueled.com on 02/03/2016.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public abstract class BaseItem<T1, T2, T3 extends BaseViewHolder> {

    protected int layoutId;
    protected ItemHandlerProvider<T2> itemHandlerProvider;

    private T1 data;
    private T3 viewHolder;

    private int positionInAdapter;
    private boolean isLastItem;


    /**
     * Constructor to use when creating a new Item.
     *
     * @param data                the data object for the Item
     * @param itemHandlerProvider the object responsible for providing the correct handler for this
     *                            item
     */
    public BaseItem(T1 data, ItemHandlerProvider<T2> itemHandlerProvider) {
        this.layoutId = getLayoutId();
        this.data = data;
        this.itemHandlerProvider = itemHandlerProvider;
    }

    /**
     * Get the position of the item in the Adapter.
     *
     * @return The position in the adapter.
     */
    public int getPositionInAdapter() {
        return positionInAdapter;
    }

    public void setPositionInAdapter(int positionInAdapter) {
        this.positionInAdapter = positionInAdapter;
    }

    /**
     * Interface that define the layout of each item
     *
     * @return the layout id to be used by the item
     */
    abstract public
    @LayoutRes
    int getLayoutId();

    /**
     * Let the caller know the item is the last one in the adapter
     *
     * @return True if it is the last item in the adapter.
     */
    public boolean isLastItem() {
        return isLastItem;
    }

    public void setIsLastItem(boolean isLastItem) {
        this.isLastItem = isLastItem;
    }

    /**
     * Called when {@link ItemsViewAdapter} needs a new {@link android.support.v7.widget.RecyclerView.ViewHolder}
     * to represent this item
     *
     * @param view the view to bind to the new view holder
     * @return a new view holder that holds the view
     */
    public abstract T3 onCreateViewHolder(View view);

    /**
     * This method should update the contents of the {@link android.support.v7.widget.RecyclerView.ViewHolder#itemView}
     * to reflect the data of this item.
     */
    public abstract void updateItemViews();

    /**
     * Returns the item type for this item.
     *
     * @return the item type for this item.
     */
    public abstract Enum getType();

    /**
     * Called by the {@link ItemsViewAdapter} to bind the provided
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} to this item. This method will also
     * invoke {@link #updateItemViews()} ()}.
     *
     * @param viewHolder the view holder to bind to this item
     */
    public void onBindViewHolder(T3 viewHolder) {
        this.viewHolder = viewHolder;
        updateItemViews();
    }

    /**
     * Returns the item data object associated with this item.
     *
     * @return item data object
     */
    public T1 getItemData() {
        return data;
    }

    /**
     * Update the data associated with this item.
     *
     * @param data The new Data
     */
    public void setitemData(T1 data) {
        this.data = data;
    }

    /**
     * Returns the item handler associated with this item.
     *
     * @return item handler
     */
    public T2 getitemHandler() {
        T2 itemHandler = null;
        if (itemHandlerProvider != null) {
            itemHandler = itemHandlerProvider.getItemHandler();
        }

        return itemHandler;
    }

    /**
     * Returns the view holder currently attached to this item.
     *
     * @return the view holder currently attached to this item
     */
    public T3 getViewHolder() {
        if (viewHolder != null && viewHolder.getItemBoundTo() == getPositionInAdapter()) {
            // Make sure the view holder is still bound to this item before returning it.
            return viewHolder;
        }

        return null;
    }

}
