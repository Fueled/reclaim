package com.fueled.reclaim.samples.hearder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fueled.reclaim.ItemsViewAdapter;
import com.fueled.reclaim.samples.PlanetItem;
import com.fueled.reclaim.samples.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Sample activity that illustrate how to use multiple items classes.
 * Created by julienFueled on 5/24/16.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public class HearderFooterActivity extends AppCompatActivity {

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

        adapter.addItemsList(createListData());

        adapter.addItem(new FooterItem("Footer OOOOOH"));
        recyclerView.setAdapter(adapter);
    }

    private List<PlanetItem> createListData() {
        ArrayList<PlanetItem> list = new ArrayList<>();
        list.add(new PlanetItem("Venus"));
        list.add(new PlanetItem("Mercury"));
        list.add(new PlanetItem("Earth"));
        list.add(new PlanetItem("Mars"));
        return list;
    }
}
