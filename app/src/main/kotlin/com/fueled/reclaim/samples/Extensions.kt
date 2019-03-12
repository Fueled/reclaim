package com.fueled.reclaim.samples

import android.view.View
import androidx.annotation.IdRes
import com.fueled.reclaim.BaseViewHolder

/**
 * Created by hussein@fueled.com on 06/09/2018.
 */

fun <T : View> BaseViewHolder.bindView(@IdRes id: Int): T = itemView.findViewById(id)