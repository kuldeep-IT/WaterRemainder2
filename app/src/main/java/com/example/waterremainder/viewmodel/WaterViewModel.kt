package com.example.waterremainder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.waterremainder.model.WaterData
import com.example.waterremainder.repositories.WaterRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaterViewModel(
    val repo: WaterRepo
): ViewModel() {

    val getAllWater : LiveData<List<WaterData>>

    init {
        getAllWater = repo.getAllWater()
    }

    fun addWater(waterData: WaterData) = CoroutineScope(Dispatchers.IO).launch {
        repo.addWater(waterData)
    }

}