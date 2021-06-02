package com.example.data

import com.example.domain.models.Answer
import com.example.domain.models.HabitDomainLayer
import com.example.domain.models.HabitDone
import com.example.data.models.HabitResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response


interface RemoteDataDataSource {
    suspend fun put(habit: HabitResponse) : Answer
    suspend fun getAll() : Response<List<HabitResponse>>
    suspend fun deleteFromServer(uid: HashMap<String,String>)
    suspend fun postDone(habitDone: HabitDone)
}

