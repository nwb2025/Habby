package com.example.hubby.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hubby.data.models.Habit
import com.example.hubby.utils.TypeConverters.TypeConv

@Database(
        entities = [Habit::class],
        version = 2
)
@TypeConverters(TypeConv::class)
abstract  class AppDataBase : RoomDatabase() {

    abstract fun habitDao(): Dao

    companion object
    {
        // for migration
        val MIGRATION_1_2:Migration = object :Migration(1,2)
        {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE habits ADD COLUMN color INTEGER NOT NULL DEFAULT -65536")
            }
        }

        @Volatile
        var INSTANCE : AppDataBase?  = null
        fun getAppDB(context: Context) : AppDataBase?
        {
            INSTANCE ?:

            synchronized( AppDataBase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,
                        "my_habitss.db"
                )
                    .addMigrations( MIGRATION_1_2)
                    .build()
            }

            return INSTANCE
        }

        // destroy the DB
        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}