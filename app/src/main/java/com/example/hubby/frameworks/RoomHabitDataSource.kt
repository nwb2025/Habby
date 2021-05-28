package com.example.hubby.frameworks

import com.example.core.data.LocalDataDataSource
import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.db.Dao
import kotlinx.coroutines.flow.Flow

class RoomHabitDataSource ( private val dao: Dao?) : LocalDataDataSource
{
    private val mapper = HabitDBMapper()

    override suspend fun add(habit: HabitDomainLayer){
        dao?.addHabit(mapper.mapToEntity(habit))
    }

    override fun getAll() : Flow<List<HabitDomainLayer>> =  dao!!.getAll()

    override suspend fun remove(habit: HabitDomainLayer){
        dao?.deleteHabit(mapper.mapToEntity(habit))
    }

    override suspend fun updateDoneDates(doneDates: String, id: String) {
        dao?.updateDoneDates ( doneDates, id)
    }

    override suspend fun updateHabit(habit: HabitDomainLayer) {
        dao?.updateHabit(mapper.mapToEntity(habit))
    }
}