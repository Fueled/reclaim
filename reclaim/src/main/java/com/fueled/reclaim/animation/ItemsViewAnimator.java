package com.fueled.reclaim.animation;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;

import com.fueled.reclaim.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussein@fueled.com on 28/03/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class ItemsViewAnimator extends SimpleItemAnimator {

    private AnimationChangeHolder removeAnimationsHolder = new AnimationChangeHolder();
    private AnimationChangeHolder addAnimationsHolder = new AnimationChangeHolder();
    private AnimationChangeHolder moveAnimationsHolder = new AnimationChangeHolder();
    private AnimationChangeHolder changesListener = new AnimationChangeHolder();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        Log.d(ItemsViewAnimator.class.getSimpleName(), "animateRemove");

        ItemAnimation removeAnimation = null;
        if (viewHolder instanceof BaseViewHolder) {
            removeAnimation = ((BaseViewHolder) viewHolder).getRemoveAnimation();

            if (removeAnimation != null) {
                removeAnimationsHolder.add(removeAnimation);
            }
        }

        return removeAnimation != null;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        Log.d(ItemsViewAnimator.class.getSimpleName(), "animateAdd");

        ItemAnimation animation = null;

        if (viewHolder instanceof BaseViewHolder) {
            animation = ((BaseViewHolder) viewHolder).getAddAnimation();

            if (animation != null) {
                addAnimationsHolder.add(animation);
            }
        }

        return animation != null;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int fromX, int fromY,
                               int toX, int toY) {
        Log.d(ItemsViewAnimator.class.getSimpleName(), "animateMove");

        ItemAnimation animation = null;

        if (viewHolder instanceof BaseViewHolder) {
            animation = ((BaseViewHolder) viewHolder).getMoveAnimation(fromX, fromY, toX, toY);

            if (animation != null) {
                moveAnimationsHolder.add(animation);
            }
        }

        return animation != null;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder,
                                 RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop,
                                 int toLeft, int toTop) {
        Log.d(ItemsViewAnimator.class.getSimpleName(), "animateChange");

        return false;
    }

    @Override
    public void runPendingAnimations() {
        removeAnimationsHolder.runAnimations();
        addAnimationsHolder.runAnimations();
        moveAnimationsHolder.runAnimations();
        changesListener.runAnimations();
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        if (item instanceof BaseViewHolder) {
            ((BaseViewHolder) item).endAnimation();
        }
    }

    @Override
    public void endAnimations() {
        removeAnimationsHolder.endRunningAnimations();
        addAnimationsHolder.endRunningAnimations();
        moveAnimationsHolder.endRunningAnimations();
        changesListener.endRunningAnimations();
    }

    @Override
    public boolean isRunning() {
        return (!removeAnimationsHolder.isEmpty() || !addAnimationsHolder.isEmpty()
                || !moveAnimationsHolder.isEmpty() || !changesListener.isEmpty());
    }

    private class AnimationChangeHolder implements ItemAnimationListener {

        private List<ItemAnimation> pendingAnimations = new ArrayList<>();
        private List<ItemAnimation> runningAnimations = new ArrayList<>();

        public boolean isEmpty() {
            return pendingAnimations.isEmpty() && runningAnimations.isEmpty();
        }

        @Override
        public void onAnimationStart(ItemAnimation animation) {
            runningAnimations.add(animation);
        }

        @Override
        public void onAnimationEnd(ItemAnimation animation) {
            runningAnimations.remove(animation);
        }

        public void endRunningAnimations() {
            for (ItemAnimation animation : runningAnimations) {
                animation.cancel();
            }
        }

        public void add(ItemAnimation disappearanceAnimation) {
            pendingAnimations.add(disappearanceAnimation);
        }

        private void runAnimations() {
            for (ItemAnimation animation : pendingAnimations) {
                animation.start();
            }

            pendingAnimations.clear();
        }
    }
}
