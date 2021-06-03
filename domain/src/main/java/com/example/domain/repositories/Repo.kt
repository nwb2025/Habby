package com.example.domain.repositories

import com.example.domain.models.Answer
import com.example.domain.models.HabitDomainLayer
import com.example.domain.models.HabitDone
import kotlinx.coroutines.flow.Flow


interface Repo {
    fun getHabits(): Flow<List<HabitDomainLayer>>
    suspend fun addHabit(habit: HabitDomainLayer)
    suspend fun put(habit: HabitDomainLayer) : Answer
    suspend fun deleteHabit(habit: HabitDomainLayer)
    suspend fun deleteFromServer(uid:HashMap<String,String>)
    suspend fun updateDoneDates(doneDates:String, id:String)
    suspend fun postDone(habit: HabitDone)
    suspend fun updateHabit(habit: HabitDomainLayer)
    fun deleteAll()
}