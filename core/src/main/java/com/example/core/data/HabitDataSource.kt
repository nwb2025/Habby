package com.example.core.data

import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import kotlinx.coroutines.flow.Flow


interface HabitDataSource
{
    suspend fun add(habit: HabitDomainLayer)
    fun getAll() : Flow<List<HabitDomainLayer>>
    fun getByType(type:String) : Flow<List<HabitDomainLayer>>
    suspend fun remove(habit: HabitDomainLayer)
    suspend fun updateDoneDates(doneDates:String, uid:String)
    suspend fun updateHabit(habit: HabitDomainLayer)

    suspend fun put(habit: HabitDomainLayer) : Answer
    suspend fun postDone(habitDone:HabitDone)
    suspend fun deleteFromServer(uid: HashMap<String,String>)
}

