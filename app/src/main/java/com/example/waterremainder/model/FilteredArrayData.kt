package com.example.waterremainder.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FilteredClass : Serializable {

    @PrimaryKey
    var id: Long = System.currentTimeMillis()

    @SerializedName("filteredClass")
    @Expose
    var listWaterData: MutableList<WaterData>? = null

    @SerializedName("date")
    @Expose
    var date: String? = ""


}