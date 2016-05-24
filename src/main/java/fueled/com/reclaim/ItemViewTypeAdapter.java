package fueled.com.reclaim;

/**
 * Created by julienFueled on 5/24/16.
 */
public interface ItemViewTypeAdapter {
    Enum<? extends ItemType> getType(int viewType);
}
