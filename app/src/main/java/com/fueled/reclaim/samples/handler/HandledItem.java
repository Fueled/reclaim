package com.fueled.reclaim.samples.handler;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fueled.reclaim.BaseItem;
import com.fueled.reclaim.BaseViewHolder;
import com.fueled.reclaim.ItemHandlerProvider;
import com.fueled.reclaim.samples.ExampleType;


/**
 * Sample item that illustrate the usage of ItemHandler.
 * Created by julienFueled on 9/12/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class HandledItem extends BaseItem<Class<? extends AppCompatActivity>, SampleHandler, HandledItem.ViewHolder> {

    /**
     * Constructor to use when creating a new card.
     *
     * @param data                the data object for the card
     * @param itemHandlerProvider the object responsible for providing the correct handler for this
     */
    public HandledItem(Class<? extends AppCompatActivity> data, ItemHandlerProvider<SampleHandler> itemHandlerProvider) {
        super(data, itemHandlerProvider);
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
        final Class<? extends AppCompatActivity> activity = getItemData();
        viewHolder.text.setText(String.format("go to %s", activity.getSimpleName()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemHandler().goToActivity(activity);
            }
        });
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.HANDLER;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
