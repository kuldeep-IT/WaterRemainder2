package com.example.waterremainder.ui

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
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
import com.example.waterremainder.utils.PreferenceKeys
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import java.util.*

class UserInfoActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityUserInfoBinding
    lateinit var dataStoreManager: DataStoreManager

    private var wakeupTime: Long = 0
    private var sleepingTime: Long = 0
    var is24h: Boolean = false
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        is24h = android.text.format.DateFormat.is24HourFormat(this.applicationContext)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        dataStoreManager = DataStoreManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.saveLongToDataStore(PreferenceKeys.WAKE_UP_TIME, 1558323000000)
            dataStoreManager.saveLongToDataStore(PreferenceKeys.SLEEP_TIME, 1558369800000)
        }


        dataStoreManager.readLongFromDataStore(PreferenceKeys.WAKE_UP_TIME).asLiveData()
            .observe(this@UserInfoActivity,
                {
                    wakeupTime = it
                })
        dataStoreManager.readLongFromDataStore(PreferenceKeys.SLEEP_TIME).asLiveData()
            .observe(this@UserInfoActivity,
                {
                    sleepingTime = it
                })





    binding.btnSubmit.setOnClickListener(this)
    binding.etWakeUp.setOnClickListener(this)
    binding.etSleep.setOnClickListener(this)

}

fun isValid(): Boolean {
    var isValidate = false

    when {
        binding.etName.text.isNullOrEmpty() ->
            Snackbar.make(
                binding.root,
                getString(R.string.str_pls_enter_name),
                Snackbar.LENGTH_SHORT
            ).show()

        binding.etWeight.text.isNullOrEmpty() ->
            Snackbar.make(
                binding.root,
                getString(R.string.str_pls_enter_weight),
                Snackbar.LENGTH_SHORT
            ).show()

        binding.etWorkOut.text.isNullOrEmpty() ->
            Snackbar.make(
                binding.root,
                getString(R.string.str_pls_enter_work_out),
                Snackbar.LENGTH_SHORT
            ).show()

        binding.etWakeUp.text.isNullOrEmpty() ->
            Snackbar.make(
                binding.root,
                getString(R.string.str_pls_slct_wake_up),
                Snackbar.LENGTH_SHORT
            ).show()

        binding.etSleep.text.isNullOrEmpty() ->
            Snackbar.make(
                binding.root,
                getString(R.string.str_pls_slct_sleep),
                Snackbar.LENGTH_SHORT
            ).show()

        else -> isValidate = true
    }
    return isValidate
}

override fun onClick(v: View?) {
    when (v?.id) {
        R.id.etWakeUp -> {
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

                    binding.etWakeUp.setText(
                        String.format(
                            "%02d:%02d",
                            selectedHr,
                            selectedMinutes
                        )
                    )
                },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), is24h
            )
            mTimePicker.setTitle("Select Wakeup Time")
            mTimePicker.show()
        }

        R.id.etSleep -> {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = sleepingTime

            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHrs, selectedMinutes ->

                    val time = Calendar.getInstance()
                    time.set(Calendar.HOUR_OF_DAY, selectedHrs)
                    time.set(Calendar.MINUTE, selectedMinutes)

                    sleepingTime = time.timeInMillis

                    binding.etSleep.setText(
                        String.format(
                            "%02d:%02d",
                            selectedHrs,
                            selectedMinutes
                        )
                    )
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24h
            )
            mTimePicker.setTitle("Select Your Sleep Time")
            mTimePicker.show()
        }


        R.id.btnSubmit -> {
            if (isValid()) {
                  CoroutineScope(Dispatchers.IO).launch {

                    dataStoreManager.saveBooleanToDataStore(PreferenceKeys.FIRST_RUN, false)
                    dataStoreManager.saveStringToDataStore(PreferenceKeys.USER_NAME, binding.etName.text.toString())
                    dataStoreManager.saveIntToDataStore(PreferenceKeys.WEIGHT_DATA, binding.etWeight.text.toString().toInt())
                    dataStoreManager.saveIntToDataStore(PreferenceKeys.WORK_OUT, binding.etWorkOut.text.toString().toInt())
                    dataStoreManager.saveLongToDataStore(PreferenceKeys.WAKE_UP_TIME, wakeupTime)
                    dataStoreManager.saveLongToDataStore(PreferenceKeys.SLEEP_TIME, sleepingTime)

                    startActivity(Intent(this@UserInfoActivity, MainActivity::class.java))
                    finish()


                }
                var userName = ""
                var workOutTime : Int = 0
                var sleepTime : Long = 0L
                var wakeupTime : Long = 0L
                var weightData : Int= 0
                var isfirst : Boolean= false

                dataStoreManager.readStringFromDataStore(PreferenceKeys.USER_NAME).asLiveData().observe(this,{
                    userName = it
                })

                dataStoreManager.readIntegerFromDataStore(PreferenceKeys.WEIGHT_DATA).asLiveData().observe(this,{
                    weightData = it
                })

                dataStoreManager.readIntegerFromDataStore(PreferenceKeys.WORK_OUT).asLiveData().observe(this,{
                    workOutTime = it
                })

                dataStoreManager.readLongFromDataStore(PreferenceKeys.SLEEP_TIME).asLiveData().observe(this,{
                    sleepTime = it
                })

                dataStoreManager.readLongFromDataStore(PreferenceKeys.WAKE_UP_TIME).asLiveData().observe(this,{
                    wakeupTime = it
                })

                dataStoreManager.readBooleanFromDataStore(PreferenceKeys.FIRST_RUN).asLiveData().observe(this,{
                    isfirst = it
                })


                lifecycleScope.launch {
                    Log.d(
                        "DATA_RESULT", "onClick: " +
                                userName + "\n" +
                                workOutTime + "\n" +
                               wakeupTime + "\n" +
                                sleepTime + "\n" +
                                isfirst + "\n" +
                                weightData+ "\n"
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