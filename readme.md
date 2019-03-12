# Reclaim [![](https://jitpack.io/v/Fueled/reclaim.svg)](https://jitpack.io/#Fueled/reclaim)

Reduce the boilerplate code that comes with using RecyclerView, decouple the code between the adapter and the item.

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
    compile 'com.github.fueled:reclaim:2.x.x'
}
```

### 1. Setup the adapter
Have your RecyclerView use the adapter [ItemViewAdapter](reclaim/src/main/kotlin/com/fueled/reclaim/ItemsViewAdapter.kt).

```kotlin
val manager = LinearLayoutManager(this)
recyclerView.setLayoutManager(manager)
adapter = ItemsViewAdapter(this)
recyclerView.setAdapter(adapter)
```

### 2. Create an adapter item
The design, implementation and interaction with items of your RecyclerView are all handled through the [AdapterItem](reclaim/src/main/kotlin/com/fueled/reclaim/AdapterItem.kt).


```kotlin
class PlanetItem(private val planetName: String) : AdapterItem<PlanetViewHolder> {

    override val layoutId: Int = android.R.layout.simple_list_item_1
    
    override fun onCreateViewHolder(view: View) = PlanetViewHolder(view)

    override fun updateItemViews(viewHolder: PlanetViewHolder) {
        viewHolder.text.setText(planetName)
    }
}

class PlanetViewHolder(view: View) : BaseViewHolder(view) {
    val text: TextView = view.findViewById(android.R.id.text1)
}

```

### 3. Add an adapter item to your adapter
Simply add instances of your adapter item to your adapter by calling [ItemViewAdapter.addItem(item: AdapterItem)](reclaim/src/main/kotlin/com/fueled/reclaim/ItemsViewAdapter.kt#L113) or [ItemViewAdapter.addItemsList(items: List<AdapterItem<*>)](reclaim/src/main/kotlin/com/fueled/reclaim/ItemsViewAdapter.kt#L45)

```kotlin
adapter = ItemsViewAdapter(this)
recyclerView.setAdapter(adapter)
for (planet in planets) {
    adapter.addItem(PlanetItem(planet))
}
```

## Using multiple item types
Adding multiple item types is very simple in Reclaim, all you need to do is create your different adapter items then add them your view adapter as shown in the example below.

Create you desired adapter items:

```kotlin
class HeaderItem(private val header: String) : AdapterItem<HeaderItemViewHolder> {
    ...
}
```
```kotlin
class FooterItem(private val footer: String) : AdapterItem<FooterItemViewHolder> {
    ...
}
```
```kotlin
class PlanetItem(private val planet: String) : AdapterItem<PlanetItemViewHolder> {
    ...
}
```

Then add them to your view adapter:
```kotlin
adapter = ItemsViewAdapter(this)
recyclerView.setAdapter(adapter)

adapter.addItem(HeaderItem("Header YEAH!"))

for (planet in planets) {
    adapter.addItem(PlanetItem(planet))
}

adapter.addItem(new FooterItem("Footer OOOOOH"))
```

## Interaction between an adapter item and other components
If you want to handle click actions that occur inside you item adapter view in your activity or fragment, you can simply pass your adapter item a handler which would be notified when a click action has occurred as shown in the example below.

```kotlin
class PlanetItem(
    private val planetName: String, 
    private val planetClickAction: (planet) -> Unit
) : AdapterItem<PlanetViewHolder> {

    override val layoutId: Int = android.R.layout.simple_list_item_1
    
    override fun onCreateViewHolder(view: View) = PlanetViewHolder(view)

    override fun updateItemViews(viewHolder: PlanetViewHolder) {
        viewHolder.text.setText(planetName)
        
        viewHolder.itemView.setOnClickListener { planetClickAction(planetName) }
    }
}

class PlanetViewHolder(view: View) : BaseViewHolder(view) {
    val text: TextView = view.findViewById(android.R.id.text1)
}
```
in your activity:

```kotlin
adapter = ItemsViewAdapter(this)
recyclerView.setAdapter(adapter)

val handler = { planet: String -> displayPlanet(planet) }
for (planet in planets) {
    adapter.addItem(PlanetItem(planet, handler))
}
```

# License

    Copyright 2019 Fueled

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
