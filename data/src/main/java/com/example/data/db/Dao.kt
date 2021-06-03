package com.example.data.db

import androidx.room.*
import androidx.room.Dao
import com.example.data.models.Habit
import com.example.domain.models.HabitDomainLayer
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    // it's like a data source
    // we return a live data object so we dont need to explicitally execute it in the background - it will be by itself
    @Query("SELECT * FROM habits")
    fun getAll() : Flow<List<Habit>>

    @Insert
    suspend fun addHabit(habit : Habit)

    @Delete
    suspend fun deleteHabit(habit : Habit)

    @Query("UPDATE habits SET done_dates = :done_dates WHERE uid = :id")
    suspend fun updateDoneDates(done_dates:String , id:String )

    @Update
    suspend fun updateHabit(habit : Habit)

    @Query("DELETE FROM habits")
    fun deleteAll()
}