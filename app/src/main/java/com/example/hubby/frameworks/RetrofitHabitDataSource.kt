package com.example.hubby.frameworks

import com.example.core.data.HabitDataSource
import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import com.example.hubby.data.api.RetrofitInstance
import com.example.hubby.utils.Constants
import kotlinx.coroutines.flow.Flow

class RetrofitHabitDataSource (private val retroApi: RetrofitInstance)  : HabitDataSource
{
    private val mapper = HabitRetrofitMapper()

    override suspend fun add(habit: HabitDomainLayer) {
        TODO("Not yet implemented")
    }


    override suspend fun put(habit: HabitDomainLayer): Answer
    {
       return  retroApi.api.putHabit(Constants.API_KEY, mapper.mapToEntity(habit))
    }

    override fun getAll(): Flow<List<HabitDomainLayer>> {
        return retroApi.api.getHabits(Constants.API_KEY)
    }

    override fun getByType(type: String): Flow<List<HabitDomainLayer>> {
        TODO("Not yet implemented")
    }

    override suspend fun remove(habit: HabitDomainLayer) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromServer(uid: HashMap<String,String>) = retroApi.api.deleteHabit(Constants.API_KEY, uid)



    override suspend fun updateDoneDates(doneDates: String, uid: String) {
        TODO("Not yet implemented")
    }



    override suspend fun postDone(habitDone: HabitDone) {
        retroApi.api.postHabit(Constants.API_KEY, habitDone)
    }

    override suspend fun updateHabit(habit: HabitDomainLayer) {
        TODO("Not yet implemented")
    }



}