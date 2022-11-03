package com.example.waterremainder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainder.databinding.ItemDateListBinding
import com.example.waterremainder.databinding.ItemWaterHistoryBinding
import com.example.waterremainder.model.FilteredClass
import com.example.waterremainder.model.WaterData

class FilterDataAdapter: RecyclerView.Adapter<FilterDataAdapter.FilterVH>()  {

    inner class FilterVH(val mBinding: ItemDateListBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val filterData = differ.currentList[position]
//            mBinding.listData = filterData.listWaterData
            mBinding.tvDate.text = filterData.date
            mBinding.executePendingBindings()
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<FilteredClass>() {
        override fun areItemsTheSame(oldItem: FilteredClass, newItem: FilteredClass): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FilteredClass, newItem: FilteredClass): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterVH {
        val mBinding = ItemDateListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FilterVH(mBinding)
    }

    override fun onBindViewHolder(holder: FilterVH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}