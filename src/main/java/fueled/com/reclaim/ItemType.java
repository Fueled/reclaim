package fueled.com.reclaim;

/**
 * Created by hussein@fueled.com on 02/03/2016.
 * Copyright (c) 2016 Fueled. All rights reserved.
 */
public interface ItemType {

    Class<? extends BaseItem> getItemClass();

    int getValue();
}
