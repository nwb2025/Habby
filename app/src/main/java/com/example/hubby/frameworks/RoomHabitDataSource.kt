package com.example.hubby.frameworks


import com.example.core.data.HabitDataSource
import com.example.core.domain.models.Answer
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import com.example.hubby.data.db.Dao
import kotlinx.coroutines.flow.Flow

class RoomHabitDataSource ( private val dao: Dao?) : HabitDataSource
{
    private val mapper = HabitDBMapper()

    override suspend fun add(habit: HabitDomainLayer)
    {
        dao?.addHabit(mapper.mapToEntity(habit))
    }

    override suspend fun put(habit: HabitDomainLayer): Answer {
        TODO("Not yet implemented")
    }

    override fun getAll() : Flow<List<HabitDomainLayer>> =  dao!!.getAll()


    override fun getByType(type: String): Flow<List<HabitDomainLayer>>  = dao?.getHabitsByType(type)!!

    override suspend fun remove(habit: HabitDomainLayer)
    {
        dao?.deleteHabit(mapper.mapToEntity(habit))
    }

    override suspend fun deleteFromServer(uid: HashMap<String, String>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDoneDates(doneDates: String, id: String) {
        dao?.updateDoneDates ( doneDates, id)
    }

    override suspend fun postDone(habitDone: HabitDone) {
        TODO("Not yet implemented")
    }

    override suspend fun updateHabit(habit: HabitDomainLayer) {
        dao?.updateHabit(mapper.mapToEntity(habit))
    }


}