package com.example.waterremainder.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore/*: DataStore<Preferences>*/ by preferencesDataStore(name = "USER_DATA_STORE")

class DataStoreManager(val context: Context) {

    /*suspend fun saveDataInt(key: String, value: Int) {
        val dataStoreKey = intPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun saveDataString(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun saveDataLong(key: String, value: Long) {
        val dataStoreKey = longPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun saveDataBoolean(key: String, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun getDataStoreString(key: String): String {
        val dataStoreKey = stringPreferencesKey(key)
        val getData = context.dataStore.data.first()
        return getData[dataStoreKey].toString()
    }

    suspend fun getDataStoreLong(key: String): Long {
        val dataStoreKey = longPreferencesKey(key)
        val getData = context.dataStore.data.first()
        return getData[dataStoreKey]!!

        *//*if (getData != null && !getData.toString().contains("")) {
            if (getData[dataStoreKey] == "null" || getData[dataStoreKey].isNullOrEmpty())
            {
                return 0L
            } else{
                return getData[dataStoreKey]!!.toLong()
            }
        } else {
            return 0L
        }*//*

    }

    suspend fun getDataStoreBoolean(key: String): Boolean {
        val dataStoreKey = booleanPreferencesKey(key)
        val getData = context.dataStore.data.first()
        return getData[dataStoreKey]!!

        *//*if (getData != null && !getData.toString().contains("") ) {
//                        return getData[dataStoreKey]!!.toBoolean()

            if (getData[dataStoreKey] == "null" || getData[dataStoreKey].isNullOrEmpty())
            {
                return false
            } else{
                return getData[dataStoreKey]!!.toBoolean()
            }

        } else {
            return true
        }*//*

    }*/


    fun readStringFromDataStore(key: Preferences.Key<String>): Flow<String> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val showCompleted = preferences[key] ?: "null"
                showCompleted
            }
    }

    fun readBooleanFromDataStore(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: false
                isChecked
            }
    }

    fun readIntegerFromDataStore(key: Preferences.Key<Int>): Flow<Int> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: -1
                isChecked
            }
    }

    fun readLongFromDataStore(key: Preferences.Key<Long>): Flow<Long> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0
                isChecked
            }
    }

    fun readFloatFromDataStore(key: Preferences.Key<Float>): Flow<Float> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0f
                isChecked
            }
    }

    suspend fun saveStringToDataStore(key: Preferences.Key<String>, name: String) {
        context.myDataStore.edit { preferences ->
            preferences[key] = name
        }
    }

    suspend fun saveBooleanToDataStore(key: Preferences.Key<Boolean>, isChecked: Boolean) {
        context.myDataStore.edit { preferences ->
            preferences[key] = isChecked
        }
    }

    suspend fun saveIntToDataStore(key: Preferences.Key<Int>, int: Int) {
        context.myDataStore.edit { preferences ->
            preferences[key] = int
        }
    }

    suspend fun saveLongToDataStore(key: Preferences.Key<Long>, long: Long) {
        context.myDataStore.edit { preferences ->
            preferences[key] = long
        }
    }

    suspend fun saveFloatToDataStore(key: Preferences.Key<Float>, float: Float) {
        context.myDataStore.edit { preferences ->
            preferences[key] = float
        }
    }




}