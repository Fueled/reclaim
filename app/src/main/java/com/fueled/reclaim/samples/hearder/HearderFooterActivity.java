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

    RecyclerView recyclerView;
    ItemsViewAdapter adapter;


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

        adapter.addItem(new HeaderItem("Header YEAH!", null));
        for (String planet : createListData()) {
            adapter.addItem(new PlanetItem(planet, null));
        }

        adapter.addItem(new FooterItem("Footer OOOOOH", null));
        recyclerView.setAdapter(adapter);
    }


    private List<String> createListData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Venus");
        list.add("Mercury");
        list.add("Earth");
        list.add("Mars");
        return list;
    }
}
