package com.example.core.data

import com.example.core.domain.models.HabitDomainLayer
import kotlinx.coroutines.flow.Flow


interface LocalDataDataSource
{
    suspend fun add(habit: HabitDomainLayer)
    fun getAll() : Flow<List<HabitDomainLayer>>
    suspend fun remove(habit: HabitDomainLayer)
    suspend fun updateDoneDates(doneDates:String, uid:String)
    suspend fun updateHabit(habit: HabitDomainLayer)

}

