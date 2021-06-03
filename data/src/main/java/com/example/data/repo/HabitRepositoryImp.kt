package com.example.data.repo

import com.example.data.LocalDataDataSource
import com.example.data.RemoteDataDataSource
import com.example.domain.models.Answer
import com.example.domain.models.HabitDomainLayer
import com.example.domain.models.HabitDone
import com.example.data.mappers.HabitDBMapper
import com.example.data.mappers.HabitRetrofitMapper
import com.example.data.models.HabitResponse
import com.example.domain.repositories.Repo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
class HabitRepositoryImp @Inject constructor(
    private val localDataSource : LocalDataDataSource,
    private val remoteDataDataSource: RemoteDataDataSource,
    private val dbMapper : HabitDBMapper,
    private val retrofitMapper : HabitRetrofitMapper
) : Repo {
    var isCacheDirty = false

    // TODO : MAKE it a Singleton !
    override fun getHabits(): Flow<List<HabitDomainLayer>>{
        val call = remoteDataDataSource.getAll()
        call.enqueue(object : Callback<List<HabitResponse>>{
            override fun onResponse(
                call: Call<List<HabitResponse>>,
                response: Response<List<HabitResponse>>
            ) {
                if(response.code() == 200){
                    CoroutineScope(Dispatchers.IO).launch {
                        refreshCache(response.body()!!.map { retrofitMapper.mapToEntity(it)})
                    }
                }
            }
            override fun onFailure(call: Call<List<HabitResponse>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return localDataSource.getAll().map { dbMapper.mapToEntityList(it) }

    }

    override suspend fun addHabit(habit : HabitDomainLayer) = localDataSource.add(dbMapper.mapFromEntity(habit))
    override suspend fun put(habit: HabitDomainLayer) : Answer =  remoteDataDataSource.put(retrofitMapper.mapFromEntity(habit))
    override suspend fun deleteHabit(habit: HabitDomainLayer) = localDataSource.remove(dbMapper.mapFromEntity(habit))
    override suspend fun deleteFromServer(uid:HashMap<String,String>) = remoteDataDataSource.deleteFromServer(uid)
    override suspend fun updateDoneDates(doneDates:String, id:String) = localDataSource.updateDoneDates(doneDates, id)
    override suspend fun postDone(habit: HabitDone) = remoteDataDataSource.postDone(habit)
    override suspend fun updateHabit(habit: HabitDomainLayer) = localDataSource.updateHabit(dbMapper.mapFromEntity(habit))
    override fun deleteAll() = localDataSource.removeAll()

    private suspend fun refreshCache(list:List<HabitDomainLayer>){
        deleteAll()
        list.forEach{
            localDataSource.add(dbMapper.mapFromEntity(it))
        }
        isCacheDirty = true
    }
}