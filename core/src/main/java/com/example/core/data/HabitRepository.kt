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

class HabitRepository (
        private val localDataSource :  LocalDataDataSource,
        private val remoteDataDataSource: RemoteDataDataSource) {
    var isCacheDirty = false

    // TODO : MAKE it a Singleton !
    // TODO: Delete extra Usecase getByType - instead of it - get the whole list and parse it

    fun getHabits(): Flow<List<HabitDomainLayer>> {
        return if (isCacheDirty)
        {
            remoteDataDataSource.getAll()
            // refreshLocalDataSource(habitListe
            localDataSource.getAll()
        }
        else{
            refreshCache()
            localDataSource.getAll()
        }

    }

    suspend fun addHabit(habit: HabitDomainLayer) = localDataSource.add(habit)
    suspend fun put(habit: HabitDomainLayer) : Answer =  remoteDataDataSource.put(habit)
    suspend fun deleteHabit(habit: HabitDomainLayer) = localDataSource.remove(habit)
    suspend fun deleteFromServer(uid:HashMap<String,String>) = remoteDataDataSource.deleteFromServer(uid)

    suspend fun updateDoneDates(doneDates:String, id:String) = localDataSource.updateDoneDates(doneDates, id)
    suspend fun postDone(habit: HabitDone) = remoteDataDataSource.postDone(habit)

    suspend fun updateHabit(habit: HabitDomainLayer) = localDataSource.updateHabit(habit)

    private fun refreshLocalDataSource(){

    }
    private fun refreshCache(){
        isCacheDirty = false
    }
}