package com.example.waterremainder.ui

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.waterremainder.MainActivity
import com.example.waterremainder.R
import com.example.waterremainder.databinding.ActivityUserInfoBinding
import com.example.waterremainder.utils.DataStoreManager
import com.example.waterremainder.utils.Keys.FIRST_RUN
import com.example.waterremainder.utils.Keys.SLEEP_TIME
import com.example.waterremainder.utils.Keys.USER_NAME
import com.example.waterremainder.utils.Keys.WAKE_UP_TIME
import com.example.waterremainder.utils.Keys.WEIGHT_DATA
import com.example.waterremainder.utils.Keys.WORK_OUT
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import java.util.*

class UserInfoActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityUserInfoBinding
    lateinit var dataStoreManager: DataStoreManager

    private var wakeupTime: Long = 0
    private var sleepingTime: Long = 0
    var is24h:Boolean =false
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         is24h = android.text.format.DateFormat.is24HourFormat(this.applicationContext)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        dataStoreManager = DataStoreManager(this)
       /* CoroutineScope(Dispatchers.IO).launch {*/
        lifecycleScope.launch {
            dataStoreManager.saveDataLong(WAKE_UP_TIME, 1558323000000)
            dataStoreManager.saveDataLong(SLEEP_TIME,1558369800000)
        }

        /*CoroutineScope(Dispatchers.Main).launch {*/
        lifecycleScope.launch {
            wakeupTime = dataStoreManager.getDataStoreLong(WAKE_UP_TIME)
            sleepingTime = dataStoreManager.getDataStoreLong(SLEEP_TIME)

            Log.d("WAKEEEWW", "onCreate: "+wakeupTime+" //////\\\\ "+ sleepingTime)

        }


        binding.btnSubmit.setOnClickListener(this)
        binding.etWakeUp.setOnClickListener(this)
        binding.etSleep.setOnClickListener(this)

    }

    fun isValid(): Boolean {
        var isValidate = false

        when{
            binding.etName.text.isNullOrEmpty() ->
                Snackbar.make(binding.root,getString(R.string.str_pls_enter_name), Snackbar.LENGTH_SHORT).show()

            binding.etWeight.text.isNullOrEmpty() ->
                Snackbar.make(binding.root,getString(R.string.str_pls_enter_weight), Snackbar.LENGTH_SHORT).show()

            binding.etWorkOut.text.isNullOrEmpty() ->
                Snackbar.make(binding.root,getString(R.string.str_pls_enter_work_out), Snackbar.LENGTH_SHORT).show()

            binding.etWakeUp.text.isNullOrEmpty() ->
                Snackbar.make(binding.root,getString(R.string.str_pls_slct_wake_up), Snackbar.LENGTH_SHORT).show()

            binding.etSleep.text.isNullOrEmpty() ->
                Snackbar.make(binding.root,getString(R.string.str_pls_slct_sleep), Snackbar.LENGTH_SHORT).show()

            else -> isValidate = true
        }
        return isValidate
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.etWakeUp ->{
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = wakeupTime

                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, selectedHr, selectedMinutes ->
                        val time = Calendar.getInstance()
                        time.set(Calendar.HOUR_OF_DAY, selectedHr)
                        time.set(Calendar.MINUTE, selectedMinutes)
                        wakeupTime = time.timeInMillis

                        binding.etWakeUp.setText(String.format("%02d:%02d", selectedHr, selectedMinutes))
                    },
                     calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24h
                )
                mTimePicker.setTitle("Select Wakeup Time")
                mTimePicker.show()
            }

            R.id.etSleep ->  {
                val calendar = Calendar.getInstance()
               calendar.timeInMillis =  sleepingTime

                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, selectedHrs, selectedMinutes ->

                        val time = Calendar.getInstance()
                        time.set(Calendar.HOUR_OF_DAY,selectedHrs)
                        time.set(Calendar.MINUTE,selectedMinutes)

                        sleepingTime = time.timeInMillis

                        binding.etSleep.setText(String.format("%02d:%02d", selectedHrs, selectedMinutes))
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    is24h
                )
                mTimePicker.setTitle("Select Your Sleep Time")
                mTimePicker.show()
            }


            R.id.btnSubmit ->  {
                if(isValid()){
                  /*  CoroutineScope(Dispatchers.IO).launch {*/
                    lifecycleScope.launch {
                        dataStoreManager.saveDataString(USER_NAME, binding.etName.text.toString())
                        dataStoreManager.saveDataString(WEIGHT_DATA, binding.etWeight.text.toString())
                        dataStoreManager.saveDataString(WORK_OUT, binding.etWorkOut.text.toString())
                        dataStoreManager.saveDataLong(WAKE_UP_TIME, wakeupTime)
                        dataStoreManager.saveDataLong(SLEEP_TIME,sleepingTime)
                        dataStoreManager.saveDataBoolean(FIRST_RUN,false)

//
                        startActivity(Intent(this@UserInfoActivity, MainActivity::class.java))
                        finish()
                        lifecycleScope.coroutineContext.cancel()

                    }

           /*         CoroutineScope(Dispatchers.Main).launch {*/
                    lifecycleScope.launch {
                        Log.d("DATA_RESULT", "onClick: "+
                                dataStoreManager.getDataStoreString(USER_NAME) + "\n"+
                                dataStoreManager.getDataStoreString(WORK_OUT) + "\n"+
                                dataStoreManager.getDataStoreLong(WAKE_UP_TIME) + "\n"+
                                dataStoreManager.getDataStoreLong(SLEEP_TIME) + "\n"+
                                dataStoreManager.getDataStoreLong(WEIGHT_DATA) + "\n"
                        )
                    }

//                    lifecycleScope.coroutineContext.cancel()/

                }
            }
        }
    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Snackbar.make(
            this.window.decorView.findViewById(android.R.id.content),
            "Please click BACK again to exit",
            Snackbar.LENGTH_SHORT
        ).show()

        lifecycleScope.launch {
            delay(1000)

            doubleBackToExitPressedOnce = false
        }
        /*
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 1000)*/


    }

}