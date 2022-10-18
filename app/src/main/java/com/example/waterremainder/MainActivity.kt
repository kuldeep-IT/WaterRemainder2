package com.example.waterremainder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.waterremainder.utils.DataStoreManager
import com.example.waterremainder.utils.Keys.FIRST_RUN
import com.example.waterremainder.utils.PreferenceKeys
import com.example.waterremainder.walkThrough.MainWalkThrough

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedEditor: SharedPreferences.Editor

    lateinit var dataStoreManager: DataStoreManager
    var isFirstRun: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        sharedEditor = sharedPreferences.edit()
        /* dataStoreManager = rember(this)  { DataStoreManager(this) }*/
        dataStoreManager = DataStoreManager(applicationContext)

        if (isItFirstRun()) {
            startActivity(Intent(this@MainActivity, MainWalkThrough::class.java))
            finish()
        }
    }

    fun isItFirstRun(): Boolean {
        if(sharedPreferences.getBoolean("isFirstRun", true)){
            sharedEditor.putBoolean("isFirstRun", false)
            sharedEditor.commit()
            sharedEditor.apply()
            return true
        } else {
            return false
        }
    }

}