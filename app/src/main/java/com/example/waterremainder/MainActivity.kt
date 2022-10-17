package com.example.waterremainder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.waterremainder.ui.UserInfoActivity
import com.example.waterremainder.utils.DataStoreManager
import com.example.waterremainder.utils.Keys.FIRST_RUN
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var dataStoreManager: DataStoreManager
    var isFirstRun: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* dataStoreManager = rember(this)  { DataStoreManager(this) }*/
        dataStoreManager = DataStoreManager(applicationContext)

        lifecycleScope.launch {

            if (dataStoreManager.getDataStoreBoolean(FIRST_RUN).toString() == "null" || dataStoreManager.getDataStoreBoolean(FIRST_RUN) == null || dataStoreManager.getDataStoreBoolean(FIRST_RUN) ||
                    dataStoreManager.getDataStoreBoolean(FIRST_RUN).toString().isNullOrBlank() ||   dataStoreManager.getDataStoreBoolean(FIRST_RUN).toString().isNullOrEmpty()
                    ) {

                    startActivity(Intent(this@MainActivity, UserInfoActivity::class.java))


            }
        }


    }
}