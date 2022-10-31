package com.example.waterremainder.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore



    const val USER_PREFERENCES_NAME = "data_store_preferences"

    //extension for data store
    val Context.myDataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    object PreferenceKeys {
       /* val MY_STRING = stringPreferencesKey("my_string")
        val MY_BOOLEAN = booleanPreferencesKey("my_boolean")
        val MY_INT = intPreferencesKey("my_int")
        val MY_LONG = longPreferencesKey("my_long")
        val MY_FLOAT = floatPreferencesKey("my_float")*/

        val USER_NAME = stringPreferencesKey("USER_NAME")
        val WORK_OUT = intPreferencesKey("WORK_OUT")
        val WAKE_UP_TIME = longPreferencesKey("WAKE_UP_TIME")
        val SLEEP_TIME = longPreferencesKey("SLEEP_TIME")
        val WEIGHT_DATA = intPreferencesKey("WEIGHT_DATA")
        val FIRST_RUN = booleanPreferencesKey("FIRST_RUN")
        val GLASS_SIZE = intPreferencesKey("GLASS_SIZE")
        val GLASS_IMG = intPreferencesKey("GLASS_IMG")
        val TAKEN_WATER_VALUE = intPreferencesKey("TAKEN_WATER_VALUE")

    }


/*const val USER_NAME = "userName"
const val WORK_OUT = "workOut"
const val WAKE_UP_TIME= "wakeUpTime"
const val SLEEP_TIME= "sleepTime"
const val WEIGHT_DATA = "weightData"
const val FIRST_RUN = "firstRun"*/
