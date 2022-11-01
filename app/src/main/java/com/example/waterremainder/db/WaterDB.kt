package com.example.waterremainder.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.waterremainder.model.WaterData

@Database(
    entities = [WaterData::class],
    version = 1
)
abstract class WaterDB : RoomDatabase() {

    abstract fun getAllWaterDao(): WaterDao

    companion object {

        @Volatile
        private var instance: WaterDB? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WaterDB::class.java,
                "water_db.db"
            ).build()
    }

}