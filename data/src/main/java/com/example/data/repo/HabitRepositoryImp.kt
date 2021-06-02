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
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

// doesn't depend on specific implementation - it gets an interface - so it makes our code more flexible
// if tomorrow we decide to use our code in web app - we dont need to change this part - 'cause it uses interfaces
// we just need to change framerworks and presentation layers
// it calls dependency inversion - when your code doesnt depend on specific impl
// control inversion - when you pass an impl in constructor

// Inject lets Dagger to know how to instances of this object
class HabitRepositoryImp @Inject constructor(
    private val localDataSource : LocalDataDataSource,
    private val remoteDataDataSource: RemoteDataDataSource,
    private val dbMapper : HabitDBMapper,
    private val retrofitMapper : HabitRetrofitMapper
) : Repo {
    var isCacheDirty = false

    // TODO : MAKE it a Singleton !

    override fun getHabits(): Flow<List<HabitDomainLayer>> {

        /*return if (!isCacheDirty){
            flow {
                val result = remoteDataDataSource.getAll()
                if (result.isSuccessful) {
                    emit(result.body()!!.map { retrofitMapper.mapToEntity(it) })
                    isCacheDirty = true
                }
                else
                    localDataSource.getAll().map { dbMapper.mapToEntityList(it) }
            }
        }else{
            localDataSource.getAll().map { dbMapper.mapToEntityList(it) }
        }*/
        return  localDataSource.getAll().map { dbMapper.mapToEntityList(it) }

    }

    override suspend fun addHabit(habit : HabitDomainLayer) = localDataSource.add(dbMapper.mapFromEntity(habit))
    override suspend fun put(habit: HabitDomainLayer) : Answer =  remoteDataDataSource.put(retrofitMapper.mapFromEntity(habit))
    override suspend fun deleteHabit(habit: HabitDomainLayer) = localDataSource.remove(dbMapper.mapFromEntity(habit))
    override suspend fun deleteFromServer(uid:HashMap<String,String>) = remoteDataDataSource.deleteFromServer(uid)
    override suspend fun updateDoneDates(doneDates:String, id:String) = localDataSource.updateDoneDates(doneDates, id)
    override suspend fun postDone(habit: HabitDone) = remoteDataDataSource.postDone(habit)
    override suspend fun updateHabit(habit: HabitDomainLayer) = localDataSource.updateHabit(dbMapper.mapFromEntity(habit))

    private suspend fun refreshCache(list:List<HabitDomainLayer>){
        list.forEach{
            localDataSource.remove(dbMapper.mapFromEntity(it))
        }
        isCacheDirty = false
    }
}