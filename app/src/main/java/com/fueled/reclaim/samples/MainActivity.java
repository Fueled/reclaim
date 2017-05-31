package com.fueled.reclaim.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fueled.reclaim.ItemsViewAdapter;
import com.fueled.reclaim.samples.handler.ItemHandlerActivity;
import com.fueled.reclaim.samples.hearder.HearderFooterActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        adapter.replaceItems(createListData());

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
}
