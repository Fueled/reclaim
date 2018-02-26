# Reclaim [![](https://jitpack.io/v/Fueled/reclaim.svg)](https://jitpack.io/#Fueled/reclaim)

Reduce the boilerplate code that comes with using RecyclerView, decouple the code between the view, the adapter and the item.

# How to
## Install
Add it in your root build.gradle at the end of repositories:
```groovy
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
Then add the dependency to the application module:
```groovy
	dependencies {
		compile 'com.github.fueled:reclaim:1.0.0'
	}
```

### 1.Setup adapter
Have your RecyclerView use for adapter [ItemViewAdapter](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java).

```java
LinearLayoutManager manager = new LinearLayoutManager(this);
manager.setOrientation(LinearLayoutManager.VERTICAL);
recyclerView.setLayoutManager(manager);
adapter = new ItemsViewAdapter(this);
recyclerView.setAdapter(adapter);
```

### 2.Create an item object
The design, implementation and interation with items of your RecyclerView are handled through the [BaseItem](reclaim/src/main/java/com/fueled/reclaim/BaseItem.java).
BastItem class has 3 parameters:

1. The data type to be display on this item.
2. The type of the handler (help facilitate communication with the other component of the application).
3. The ViewHolder class to use.

```java
public class PlanetItem extends BaseItem<String, Void, PlanetItem.ViewHolder> {

    public PlanetItem(String data, ItemHandlerProvider<Void> itemPresenterProvider) {
        super(data, itemPresenterProvider);
    }

    @Override
    public int getLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void updateItemViews() {
        ViewHolder viewHolder = getViewHolder();
        String planetName = getItemData();
        viewHolder.text.setText(planetName);
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.PLANET;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
```

### 3.Add the item object to the adapter
Simple add instances of your item object with [ItemViewAdapter.addItem(BaseItem item)](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java#L67) or [ItemViewAdapter.addItemsList(List<? extends BaseItem> items)](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java#L35)

```java
adapter = new ItemsViewAdapter(this);
recyclerView.setAdapter(adapter);
for (String planet : createListData()) {
    adapter.addItem(new PlanetItem(planet, null));
}
```

## Using multiple Item types
Because of the strong decoupling introduced by the Reclaim, it is easy to use multiple item class and implement [header/footer](app/src/main/java/com/fueled/reclaim/samples/hearder), complex menu etc...

Simple create items as explained above, and add them to the adapter at the position they should be displayed.

```java
adapter = new ItemsViewAdapter(this);

adapter.addItem(new HeaderItem("Header YEAH!", null));
for (String planet : createListData()) {
    adapter.addItem(new PlanetItem(planet, null));
}
adapter.addItem(new FooterItem("Footer OOOOOH", null));

recyclerView.setAdapter(adapter);
```

## Interaction between item object and other components
More often then not, each item of a list contains interation with other component of your application; starting a new Activity, triggering an HTTP call, updating other UI component etc...

In that you can pass a `ItemHandler` object to your item. This `ItemHandler` can be an Activity, a Fragment, a helper class etc...

**Caution: the object passed to the construtor of your item needs to implement [ItemHandlerProvider](reclaim/src/main/java/com/fueled/reclaim/ItemHandlerProvider.java) and not the ItemHandler itself.**

```java
public class HandledItem extends BaseItem<Class<? extends AppCompatActivity>, SampleHandler, HandledItem.ViewHolder> {
    public HandledItem(Class<? extends AppCompatActivity> data, ItemHandlerProvider<SampleHandler> itemPresenterProvider) {
        super(data, itemPresenterProvider);
    }

    @Override
    public int getLayoutId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void updateItemViews() {
        ViewHolder viewHolder = getViewHolder();
        final Class<? extends AppCompatActivity> activity = getItemData();
        viewHolder.text.setText(String.format("go to %s", activity.getSimpleName()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitemHandler().goToActivity(activity);
            }
        });
    }

    @Override
    public Enum<ExampleType> getType() {
        return ExampleType.HANDLER;
    }

    public static class ViewHolder extends BaseViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
```
in the activity:

```java
public class ItemHandlerActivity extends AppCompatActivity implements ItemHandlerProvider<SampleHandler>, SampleHandler {
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
        adapter.addItem(new HandledItem(MainActivity.class, this));
        adapter.addItem(new HandledItem(HearderFooterActivity.class, this));
        adapter.addItem(new FooterItem("Footer OOOOOH", null));
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
```

# License

    Copyright 2016 Fueled

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
