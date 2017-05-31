package com.fueled.reclaim.samples.handler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fueled.reclaim.ItemHandlerProvider;
import com.fueled.reclaim.ItemsViewAdapter;
import com.fueled.reclaim.samples.MainActivity;
import com.fueled.reclaim.samples.R;
import com.fueled.reclaim.samples.hearder.FooterItem;
import com.fueled.reclaim.samples.hearder.HeaderItem;
import com.fueled.reclaim.samples.hearder.HearderFooterActivity;

/**
 * Sample activity that illustrate the usage of ItemHandlers.
 * Created by julienFueled on 9/12/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class ItemHandlerActivity extends AppCompatActivity implements
        ItemHandlerProvider<SampleHandler>, SampleHandler {

    private RecyclerView recyclerView;
    private ItemsViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //set adapter
        adapter = new ItemsViewAdapter(this);

        adapter.addItem(new HeaderItem("Header YEAH!"));
        adapter.addItem(new HandledItem(MainActivity.class, this));
        adapter.addItem(new HandledItem(HearderFooterActivity.class, this));
        adapter.addItem(new FooterItem("Footer OOOOOH"));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public SampleHandler getItemHandler() {
        return this;
    }

    @Override
    public void goToActivity(Class<? extends AppCompatActivity> activity) {
        startActivity(new Intent(this, activity));
        finish();
    }
}
