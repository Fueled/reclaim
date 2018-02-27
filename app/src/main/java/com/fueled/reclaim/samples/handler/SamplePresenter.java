package com.fueled.reclaim.samples.handler;

import android.support.v7.app.AppCompatActivity;

/**
 * Sample interface for the ItemHandler used in the Handler demo activity.
 * Created by julienFueled on 9/12/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public interface SamplePresenter {

    void goToActivity(Class<? extends AppCompatActivity> activity);
}
