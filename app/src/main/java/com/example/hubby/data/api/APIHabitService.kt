package com.example.hubby.data.api


import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import com.example.hubby.data.models.HabitResponse
import com.example.hubby.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface APIHabitService
{
    // this interface will be used by the retrofit library to fetch data from API

    @GET(Constants._URL)
    fun getHabits(@Header("Authorization") token:String) : Flow<List<HabitDomainLayer>>

    @PUT(Constants._URL)
    suspend fun  putHabit(@Header("Authorization") token: String,
                         @Body habitResponse: HabitResponse) : Answer

    // TODO : we need to return something to check it later
    @HTTP(method = "DELETE", path = Constants._URL, hasBody = true )
    suspend fun  deleteHabit(@Header("Authorization") token:String ,
                             @Body uid: HashMap<String,String>)

    @POST(Constants.POST_URL)
    suspend fun postHabit(@Header("Authorization") token:String ,
                          @Body habitDone: HabitDone)
}