package com.inkbird.inkbirdapp.device.ibsm2sbt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.inkbird.inkbirdapp.R

class SelectorAdapter(resId: Int, data: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(resId, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.item, item)
    }
}