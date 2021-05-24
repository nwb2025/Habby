package com.example.core.data

import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import kotlinx.coroutines.flow.Flow

// doesn't depend on specific implementation - it gets an interface - so it makes our code more flexible
// if tomorrow we decide to use our code in web app - we dont need to change this part - 'cause it uses interfaces
// we just need to change framerworks and presentation layers
// it calls dependency inversion - when your code doesnt depend on specific impl
// control inversion - when you pass an impl in constructor

class HabitRepository ( private val dataSource :  HabitDataSource )
{
    suspend fun addHabit(habit:HabitDomainLayer) = dataSource.add(habit)

    suspend fun put(habit: HabitDomainLayer) : Answer {
        return dataSource.put(habit)
    }

    fun getAll() : Flow<List<HabitDomainLayer>> = dataSource.getAll()

    fun getByType(type:String) : Flow<List<HabitDomainLayer>> = dataSource.getByType(type)

    suspend fun deleteHabit(habit: HabitDomainLayer) = dataSource.remove(habit)

    suspend fun deleteFromServer(uid:HashMap<String,String>) = dataSource.deleteFromServer(uid)

    suspend fun updateDoneDates(doneDates:String, id:String) = dataSource.updateDoneDates(doneDates, id)

    suspend fun postDone(habit:HabitDone) = dataSource.postDone(habit)

    suspend fun updateHabit(habit: HabitDomainLayer) = dataSource.updateHabit(habit)
}