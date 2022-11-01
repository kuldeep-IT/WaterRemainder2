package com.example.waterremainder.repositories

import androidx.lifecycle.LiveData
import com.example.waterremainder.db.WaterDB
import com.example.waterremainder.model.WaterData

class WaterRepo(val db: WaterDB) {

    fun addWater(waterData: WaterData) = db.getAllWaterDao().addWater(waterData)

    fun getAllWater(): LiveData<List<WaterData>> = db.getAllWaterDao().getAllWater()
}