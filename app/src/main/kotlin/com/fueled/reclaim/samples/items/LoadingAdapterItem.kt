package com.fueled.reclaim.samples.items

import com.fueled.reclaim.AdapterItem
import com.fueled.reclaim.BaseViewHolder
import com.fueled.reclaim.samples.R

/**
 * Created by shashank@fueled.com on 08/04/2020.
 */
class LoadingAdapterItem : AdapterItem<LoadingViewHolder>() {

    override val layoutId = R.layout.item_loading

    override fun onCreateViewHolder(view: View) = LoadingViewHolder(view)

}

class LoadingViewHolder(view: View) : BaseViewHolder(view)