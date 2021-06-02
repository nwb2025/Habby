package com.example.hubby.frameworks

import com.example.data.RemoteDataDataSource
import com.example.data.models.Habit
import com.example.domain.models.Answer
import com.example.domain.models.HabitDomainLayer
import com.example.domain.models.HabitDone
import com.example.data.models.HabitResponse
import com.example.data.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RetrofitHabitDataSource  @Inject constructor
    (private val retroApi: RetrofitInstance) : RemoteDataDataSource {

    override suspend fun put(habit : HabitResponse): Answer {
        // TODO: must be fixed !
       return  retroApi.api.putHabit(Constants.API_KEY, habit)
    }

    override suspend fun getAll(): Response<List<HabitResponse>> {
        return retroApi.api.getHabits(Constants.API_KEY)
    }
    override suspend fun deleteFromServer(uid : HashMap<String,String>) = retroApi.api.deleteHabit(Constants.API_KEY, uid)

    override suspend fun postDone(habitDone: HabitDone) {
        retroApi.api.postHabit(Constants.API_KEY, habitDone)
    }


}