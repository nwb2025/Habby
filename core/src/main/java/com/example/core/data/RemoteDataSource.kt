package com.example.core.data

import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import kotlinx.coroutines.flow.Flow


interface RemoteDataDataSource {
    suspend fun put(habit: HabitDomainLayer) : Answer
    fun getAll() : Flow<List<HabitDomainLayer>>
    suspend fun deleteFromServer(uid: HashMap<String,String>)
    suspend fun postDone(habitDone: HabitDone)
}

