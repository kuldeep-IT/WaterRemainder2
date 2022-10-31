package com.example.waterremainder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainder.databinding.LayoutWaterBottlesBinding
import com.example.waterremainder.model.BottleSizeData

class DialogBottleAdapter(val context: Context, var list: ArrayList<BottleSizeData>) :
    RecyclerView.Adapter<DialogBottleAdapter.DialogBottleVH>() {

    inner class DialogBottleVH(private val binding: LayoutWaterBottlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bottelSizeData: BottleSizeData) {
            binding.ivBottle.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    bottelSizeData.imageBottle
                )
            )
            binding.tvSize.text = bottelSizeData.size.toString()

           /* itemView.setOnClickListener {
                onItemClicked?.onClick()
            }*/
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogBottleVH {
        return DialogBottleVH(
            LayoutWaterBottlesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DialogBottleVH, position: Int) {
        val data = list[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            onItemClicked?.onClick(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var onItemClicked: onClickListener? = null

    interface onClickListener {
        fun onClick(bottleSizeData: BottleSizeData)
    }


}