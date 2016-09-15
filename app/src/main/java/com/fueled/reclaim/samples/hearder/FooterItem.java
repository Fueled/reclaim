package com.fueled.reclaim.samples.hearder;

import android.view.View;
import android.widget.TextView;

import com.fueled.reclaim.BaseItem;
import com.fueled.reclaim.BaseViewHolder;
import com.fueled.reclaim.ItemHandlerProvider;
import com.fueled.reclaim.samples.ExampleType;
import com.fueled.reclaim.samples.R;


/**
 * Sample item to be used as a footer in a recyclerView.
 * Created by julienFueled on 5/24/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class FooterItem extends BaseItem<String, Void, FooterItem.ViewHolder> {

    /**
     * Constructor to use when creating a new card.
     *
     * @param data                the data object for the card
     * @param itemHandlerProvider the object responsible for providing the correct handler for this
     */
    public FooterItem(String data, ItemHandlerProvider<Void> itemHandlerProvider) {
        super(data, itemHandlerProvider);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_footer;
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void updateItemViews() {
        ViewHolder viewHolder = getViewHolder();
        String footerText = getItemData();
        viewHolder.text.setText(footerText);
    }


    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.FOOTER;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.footer_text);
        }
    }
}
