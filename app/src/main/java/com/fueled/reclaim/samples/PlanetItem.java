package com.fueled.reclaim.samples;

import android.view.View;
import android.widget.TextView;

import com.fueled.reclaim.BaseItem;
import com.fueled.reclaim.BaseViewHolder;


/**
 * Sample Item that will display a simple line of text.
 * Created by julienFueled on 5/24/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class PlanetItem extends BaseItem<String, Void, PlanetItem.ViewHolder> {

    /**
     * Constructor to use when creating a new card.
     *
     * @param data the data object for the card
     */
    public PlanetItem(String data) {
        super(data, null);
    }

    @Override
    public int getLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void updateItemViews() {
        ViewHolder viewHolder = getViewHolder();
        String planetName = getItemData();
        viewHolder.text.setText(planetName);
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.PLANET;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
