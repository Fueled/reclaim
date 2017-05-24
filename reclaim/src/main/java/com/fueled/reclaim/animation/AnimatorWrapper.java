package com.fueled.reclaim.animation;

import android.animation.Animator;
import android.support.annotation.NonNull;

/**
 * Created by hussein@fueled.com on 06/06/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class AnimatorWrapper extends AbstractAnimatorWrapper implements Animator.AnimatorListener {

    private Animator animator;

    public AnimatorWrapper(@NonNull Animator animator) {
        this.animator = animator;

        animator.addListener(this);
    }

    @Override
    public void start() {
        animator.start();
    }

    @Override
    public void cancel() {
        animator.cancel();
    }

    @Override
    public void onAnimationStart(Animator animator) {
        notifyAnimationStart();
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        notifyAnimationStart();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
