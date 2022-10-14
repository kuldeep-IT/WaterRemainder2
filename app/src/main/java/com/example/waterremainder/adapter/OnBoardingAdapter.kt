package com.example.waterremainder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainder.databinding.ActivityWalkThroughBinding
import com.example.waterremainder.model.OnBoardingData

class PageOnBoardingAdapter(var list: ArrayList<OnBoardingData>) : RecyclerView.Adapter<PageOnBoardingAdapter.OnBoardingViewHolder>(){

    var dataList = ArrayList<OnBoardingData>()


     class OnBoardingViewHolder(private val binding: ActivityWalkThroughBinding)
        : RecyclerView.ViewHolder(binding.root){

            //another method for binding data to OnBindViewHolder
            fun bind(onBoardingData: OnBoardingData){
                binding.animationView.setAnimation(onBoardingData.lottie!!)
                binding.tvTitle.text = onBoardingData.title
                binding.tvDescription.text = onBoardingData.description
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ActivityWalkThroughBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}