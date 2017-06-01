package com.fueled.reclaim;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by hussein@fueled.com on 24/05/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class DiffChecker extends DiffUtil.Callback {

    private List<? extends BaseItem> oldItemsList;
    private List<? extends BaseItem> newItemsList;

    public DiffChecker(List<? extends BaseItem> oldItemsList, List<? extends BaseItem> newItemsList) {
        this.oldItemsList = oldItemsList;
        this.newItemsList = newItemsList;
    }

    @Override
    public int getOldListSize() {
        return oldItemsList.size();
    }

    @Override
    public int getNewListSize() {
        return newItemsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return getOldItem(oldItemPosition).isTheSame(getNewItem(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return getOldItem(oldItemPosition).isContentsTheSame(getNewItem(newItemPosition));
    }

    private BaseItem getOldItem(int position) {
        return oldItemsList.get(position);
    }

    private BaseItem getNewItem(int position) {
        return newItemsList.get(position);
    }

    public static DiffUtil.DiffResult calculateDiff(List<? extends BaseItem> oldItemsList,
                                                    List<? extends BaseItem> newItemsList) {
        return DiffUtil.calculateDiff(new DiffChecker(oldItemsList, newItemsList));
    }
}
