package com.example.data

import com.example.data.models.Habit
import com.example.domain.models.HabitDomainLayer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface LocalDataDataSource
{
    suspend fun add(habit : Habit)
    fun getAll() : Flow<List<Habit>>
    suspend fun remove(habit : Habit)
    suspend fun updateDoneDates(doneDates : String, uid : String)
    suspend fun updateHabit(habit : Habit)
    fun removeAll()

}

