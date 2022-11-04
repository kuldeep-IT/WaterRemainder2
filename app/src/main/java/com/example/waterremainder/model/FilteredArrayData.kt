package com.example.waterremainder.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FilteredClass : Serializable {

    @PrimaryKey
    var id: Long = System.currentTimeMillis()

    @SerializedName("filteredClass")
    @Expose
    var listWaterData: MutableLiveData<List<WaterData>> = MutableLiveData<List<WaterData>>(
        arrayListOf())

    @SerializedName("date")
    @Expose
    var date: String? = ""


}