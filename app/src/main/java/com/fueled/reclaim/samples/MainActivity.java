package com.fueled.reclaim.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fueled.reclaim.ItemHandlerProvider;
import com.fueled.reclaim.ItemsViewAdapter;
import com.fueled.reclaim.animation.ItemsViewAnimator;
import com.fueled.reclaim.samples.handler.ItemHandlerActivity;
import com.fueled.reclaim.samples.hearder.HearderFooterActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ItemHandlerProvider<MainActivity> {

    private RecyclerView recyclerView;
    private ItemsViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new ItemsViewAnimator());

        adapter = new ItemsViewAdapter(this);
        recyclerView.setAdapter(adapter);

        addMoreItems();
        addMoreItems();
    }

    public void addMoreItems() {
        adapter.addItemsList(createListData());
    }

    public void addItem(int position) {
        adapter.addItem(position + 1, new PlanetItem("Test", 0xFF000000, this));
    }

    private List<PlanetItem> createListData() {
        ArrayList<PlanetItem> list = new ArrayList<>();
        list.add(new PlanetItem("Venus", 0xFFF44336, this));
        list.add(new PlanetItem("Mercury", 0xFFE91E63, this));
        list.add(new PlanetItem("Earth", 0xFF673AB7, this));
        list.add(new PlanetItem("Mars", 0xFF2196F3, this));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu); //Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.headerMenu:
                startActivity(new Intent(this, HearderFooterActivity.class));
                finish();
                return true;
            case R.id.handlerMenu:
                startActivity(new Intent(this, ItemHandlerActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public MainActivity getItemHandler() {
        return this;
    }
}
