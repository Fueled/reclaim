package com.fueled.reclaim;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hussein@fueled.com on 02/03/2016.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int itemBoundTo;

    public BaseViewHolder(View view) {
        super(view);
    }

    public int getItemBoundTo() {
        return itemBoundTo;
    }

    public void setItemBoundTo(int itemBoundTo) {
        this.itemBoundTo = itemBoundTo;
    }
}
