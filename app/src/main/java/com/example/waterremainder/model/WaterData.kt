package com.example.waterremainder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "waterRemainder")
data class WaterData(

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var glassSize : Int?,

    var time: String?,

    var takenWater : Int?,

    var valueOfTheDay: String?
):Serializable