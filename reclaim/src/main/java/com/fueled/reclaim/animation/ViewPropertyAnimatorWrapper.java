package com.fueled.reclaim.animation;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.ViewPropertyAnimator;

/**
 * Created by hussein@fueled.com on 06/06/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class ViewPropertyAnimatorWrapper extends AbstractAnimatorWrapper implements
        Animator.AnimatorListener {

    private ViewPropertyAnimator animator;

    public ViewPropertyAnimatorWrapper(@NonNull ViewPropertyAnimator animator) {
        this.animator = animator;

        animator.setListener(this);
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
        notifyAnimationEnd();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
