package com.fueled.reclaim.samples;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fueled.reclaim.BaseItem;
import com.fueled.reclaim.BaseViewHolder;
import com.fueled.reclaim.ItemHandlerProvider;
import com.fueled.reclaim.animation.AnimationHelper;
import com.fueled.reclaim.animation.ItemAnimation;


/**
 * Sample Item that will display a simple line of text.
 * Created by julienFueled on 5/24/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class PlanetItem extends BaseItem<String, MainActivity, PlanetItem.ViewHolder> {

    private int color;

    /**
     * Constructor to use when creating a new card.
     *
     * @param data the data object for the card
     */
    public PlanetItem(String data, int color, ItemHandlerProvider<MainActivity> handler) {
        super(data, handler);

        this.color = color;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_planet;
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
        viewHolder.text.setBackgroundColor(color);

        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getViewHolder() != null) {
                    getItemHandler().addItem(getViewHolder().getAdapterPosition());
                }
            }
        });
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.PLANET;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.planet_name_text_view);
        }

        @Override
        public ItemAnimation getAddAnimation() {
            itemView.setTranslationX(-itemView.getRight());
            return AnimationHelper.getItemAnimation(
                    itemView.animate().translationX(0).setDuration(500)
            );
        }

    }
}
