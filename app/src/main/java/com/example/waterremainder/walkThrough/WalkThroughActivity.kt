package com.example.waterremainder.walkThrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waterremainder.R
import com.example.waterremainder.model.OnBoardingData

class WalkThroughActivity : AppCompatActivity() {

    private val itemList = ArrayList<OnBoardingData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)
    }
}