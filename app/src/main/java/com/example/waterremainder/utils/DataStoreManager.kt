package com.example.waterremainder.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore/*: DataStore<Preferences>*/ by preferencesDataStore(name = "USER_DATA_STORE")

class DataStoreManager(val context: Context) {


    suspend fun saveDataInt(key: String, value: Int) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value.toString()
        }
    }

    suspend fun saveDataString(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun saveDataLong(key: String, value: Long) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value.toString()
        }
    }

    suspend fun saveDataBoolean(key: String, value: Boolean) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value.toString()
        }
    }

    suspend fun getDataStoreString(key: String): String {
        val dataStoreKey = stringPreferencesKey(key)
        val getData = context.dataStore.data.first()
        return getData[dataStoreKey].toString()
    }

    suspend fun getDataStoreLong(key: String): Long {
        val dataStoreKey = stringPreferencesKey(key)
        val getData = context.dataStore.data.first()
        return getData[dataStoreKey]!!.toLong()
    }

    suspend fun getDataStoreBoolean(key: String): Boolean {
        val dataStoreKey = stringPreferencesKey(key)
        val getData = context.dataStore.data.first()

        if (getData != null && !getData.toString().contains("") ) {
//                        return getData[dataStoreKey]!!.toBoolean()

            if (getData[dataStoreKey] == "null" + getData[dataStoreKey].isNullOrEmpty())
            {
                return true
            } else{
                return getData[dataStoreKey]!!.toBoolean()
            }

        } else {
            return true
        }

    }

}