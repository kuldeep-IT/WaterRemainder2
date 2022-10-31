package com.example.waterremainder.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.waterremainder.model.WaterData

@Dao
interface WaterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWater(waterData: WaterData): Long

    @Query("SELECT * FROM waterRemainder")
    fun getAllWater(): LiveData<List<WaterData>>

}