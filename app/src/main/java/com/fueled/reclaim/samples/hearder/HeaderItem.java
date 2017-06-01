package com.fueled.reclaim.samples.hearder;

import android.view.View;
import android.widget.TextView;

import com.fueled.reclaim.BaseItem;
import com.fueled.reclaim.BaseViewHolder;
import com.fueled.reclaim.samples.ExampleType;
import com.fueled.reclaim.samples.R;

/**
 * Sample item to be used as a footer in a recyclerView.
 * Created by julienFueled on 5/24/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class HeaderItem extends BaseItem<String, Void, HeaderItem.ViewHolder> {


    /**
     * Constructor to use when creating a new card.
     *
     * @param data the data object for the card
     */
    public HeaderItem(String data) {
        super(data, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_header;
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void updateItemViews() {
        ViewHolder viewHolder = getViewHolder();
        String headerText = getItemData();
        viewHolder.text.setText(headerText);
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.HEADER;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.header_text);
        }
    }
}
