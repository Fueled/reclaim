package com.fueled.reclaim.animation;

/**
 * Created by hussein@fueled.com on 28/03/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public interface ItemAnimation {

    void start();

    void cancel();

    void addListener(ItemAnimationListener animationListener);

    void removeListener(ItemAnimationListener animationListener);

}
