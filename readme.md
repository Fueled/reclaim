#Reclaim
Reduce the boilerplate code that comes with using RecyclerView, decouple the code between the view, the adapter and the item.

#How to
##Install
TDB

##1.Setup adapter
Have your RecyclerView use for adapter [ItemViewAdapter](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java).

```java
LinearLayoutManager manager = new LinearLayoutManager(this);
manager.setOrientation(LinearLayoutManager.VERTICAL);
recyclerView.setLayoutManager(manager);
adapter = new ItemsViewAdapter(this);
recyclerView.setAdapter(adapter);
```

##2.Create an item object
The design, implementation and interation with items of your RecyclerView are handled through the [BaseItem](reclaim/src/main/java/com/fueled/reclaim/BaseItem.java).
BastItem class has 3 parameters:

1. The data type to be display on this item.
2. The type of the handler (help facilitate communication with the other component of the application).
3. The ViewHolder class to use.

```java
public class PlanetItem extends BaseItem<String, Void, PlanetItem.ViewHolder> {

    public PlanetItem(String data, ItemHandlerProvider<Void> itemHandlerProvider) {
        super(data, itemHandlerProvider);
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

##3.Add the item object to the adapter
Simple add instances of your item object with [ItemViewAdapter.addItem(BaseItem item)](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java#L67) or [ItemViewAdapter.addItemsList(List<? extends BaseItem> items)](reclaim/src/main/java/com/fueled/reclaim/ItemsViewAdapter.java#L35)

```JAVA
adapter = new ItemsViewAdapter(this);
recyclerView.setAdapter(adapter);
for (String planet : createListData()) {
    adapter.addItem(new PlanetItem(planet, null));
}
```