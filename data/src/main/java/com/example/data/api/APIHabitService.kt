package com.example.data.api

import com.example.domain.models.Answer
import com.example.domain.models.HabitDone
import com.example.data.models.HabitResponse
import com.example.data.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIHabitService
{
    @GET(Constants._URL)
    fun getHabits(@Header("Authorization") token:String) : Call<List<HabitResponse>>

    @PUT(Constants._URL)
    suspend fun  putHabit(@Header("Authorization") token: String,
                         @Body habitResponse: HabitResponse
    ) : Answer

    // TODO : we need to return something to check it later
    @HTTP(method = "DELETE", path = Constants._URL, hasBody = true )
    suspend fun  deleteHabit(@Header("Authorization") token:String ,
                             @Body uid: HashMap<String,String>)
    @POST(Constants.POST_URL)
    suspend fun postHabit(@Header("Authorization") token:String ,
                          @Body habitDone: HabitDone
    )
}