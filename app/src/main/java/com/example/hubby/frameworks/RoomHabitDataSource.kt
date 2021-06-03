package com.example.hubby.frameworks

import com.example.data.LocalDataDataSource
import com.example.data.db.Dao
import com.example.domain.models.HabitDomainLayer
import com.example.data.mappers.HabitDBMapper
import com.example.data.models.Habit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomHabitDataSource  @Inject constructor ( private val dao: Dao?) : LocalDataDataSource
{
    override suspend fun add(habit: Habit){
        dao?.addHabit(habit)
    }

    override fun getAll() : Flow<List<Habit>> =  dao!!.getAll()

    override suspend fun remove(habit: Habit){
        dao?.deleteHabit(habit)
    }

    override suspend fun updateDoneDates(doneDates: String, id: String) {
        dao?.updateDoneDates (doneDates, id)
    }

    override suspend fun updateHabit(habit: Habit) {
        dao?.updateHabit(habit)
    }

    override fun removeAll() {
        dao?.deleteAll()
    }
}