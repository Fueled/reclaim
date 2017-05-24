package com.fueled.reclaim.animation;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussein@fueled.com on 06/06/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public abstract class AbstractAnimatorWrapper implements ItemAnimation {

    protected List<ItemAnimationListener> animationListeners;

    public AbstractAnimatorWrapper() {
        animationListeners = new ArrayList<>();
    }

    @Override
    public void addListener(@NonNull ItemAnimationListener animationListener) {
        animationListeners.add(animationListener);
    }

    @Override
    public void removeListener(@NonNull ItemAnimationListener animationListener) {
        animationListeners.remove(animationListener);
    }

    protected void notifyAnimationStart() {
        for (ItemAnimationListener animationListener : animationListeners) {
            animationListener.onAnimationStart(this);
        }
    }

    protected void notifyAnimationEnd() {
        for (ItemAnimationListener animationListener : animationListeners) {
            animationListener.onAnimationEnd(this);
        }
    }
}
