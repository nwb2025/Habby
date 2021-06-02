package com.example.hubby.frameworks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.db.Dao
import com.example.data.utils.TypeConverters.TypeConv

@Database(
        entities = [com.example.data.models.Habit::class],
        version = 2
)
@TypeConverters(TypeConv::class)
abstract  class AppDataBase : RoomDatabase() {

    abstract fun habitDao(): Dao

    companion object
    {
        @Volatile
        var INSTANCE : AppDataBase?  = null
        fun getAppDB(context: Context) : AppDataBase
        {
            INSTANCE ?:

            synchronized( AppDataBase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,
                        "hatt.db"
                )
                    .build()
            }

            return INSTANCE!!
        }

        // destroy the DB
        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}