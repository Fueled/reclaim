package com.fueled.reclaim;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fueled.reclaim.animation.AnimationHelper;
import com.fueled.reclaim.animation.ItemAnimation;

/**
 * Created by hussein@fueled.com on 02/03/2016.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected static final int DEFAULT_ANIMATION_DURATION = 300;

    protected ItemAnimation currentAnimation;

    public BaseViewHolder(View view) {
        super(view);
    }

    public ItemAnimation getRemoveAnimation() {
        itemView.setAlpha(1);

        currentAnimation = AnimationHelper.getItemAnimation(
                itemView.animate().alpha(0).setDuration(DEFAULT_ANIMATION_DURATION)
        );

        return currentAnimation;
    }

    public ItemAnimation getAddAnimation() {
        itemView.setAlpha(0);

        currentAnimation = AnimationHelper.getItemAnimation(
                itemView.animate().alpha(1).setDuration(DEFAULT_ANIMATION_DURATION)
        );

        return currentAnimation;
    }

    public ItemAnimation getMoveAnimation(int fromX, int fromY, int toX, int toY) {
        itemView.setX(fromX);
        itemView.setY(fromY);
        currentAnimation = AnimationHelper.getItemAnimation(
                itemView.animate().x(toX).y(toY).setDuration(DEFAULT_ANIMATION_DURATION)
        );

        return currentAnimation;
    }

    public void endAnimation() {
        if (currentAnimation != null) {
            currentAnimation.cancel();
        }
    }
}
