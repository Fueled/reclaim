package com.fueled.reclaim.animation;

import android.animation.Animator;
import android.view.ViewPropertyAnimator;

/**
 * Created by hussein@fueled.com on 06/06/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */
public class AnimationHelper {

    public static ItemAnimation getItemAnimation(Animator animator) {
        return new AnimatorWrapper(animator);
    }

    public static ItemAnimation getItemAnimation(ViewPropertyAnimator animator) {
        return new ViewPropertyAnimatorWrapper(animator);
    }

}
