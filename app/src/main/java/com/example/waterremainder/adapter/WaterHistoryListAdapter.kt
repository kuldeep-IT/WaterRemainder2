package com.example.waterremainder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainder.databinding.ItemWaterHistoryBinding
import com.example.waterremainder.model.WaterData

class WaterHistoryListAdapter : RecyclerView.Adapter<WaterHistoryListAdapter.WaterHistoryVH>() {

    inner class WaterHistoryVH(val mBinding: ItemWaterHistoryBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val waterData = differ.currentList[position]
            mBinding.waterVM = waterData

            mBinding.executePendingBindings()
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<WaterData>() {
        override fun areItemsTheSame(oldItem: WaterData, newItem: WaterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WaterData, newItem: WaterData): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterHistoryVH {
        val mBinding = ItemWaterHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return WaterHistoryVH(mBinding)
    }

    override fun onBindViewHolder(holder: WaterHistoryVH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}