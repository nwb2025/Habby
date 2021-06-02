package com.example.data.mappers

import com.example.domain.models.HabitDomainLayer
import com.example.data.models.Habit
import javax.inject.Inject

class HabitDBMapper :
    EntityMapper<HabitDomainLayer, Habit>
{
    override fun mapToEntity(entity : Habit): HabitDomainLayer {
        return HabitDomainLayer(
            uid = entity.uid,
            count =  entity.count,
            date = entity.date,
            description = entity.description,
            frequency = entity.frequency,
            priority = entity.priority,
            name = entity.name,
            type = entity.type,
            color = entity.color,
            done_dates = entity.done_dates
        )
    }

    override fun mapFromEntity(domainModel: HabitDomainLayer): Habit {
        return Habit(
            uid = domainModel.uid!!,
            count = domainModel.count,
            date = domainModel.date,
            description = domainModel.description,
            frequency = domainModel.frequency,
            priority = domainModel.priority,
            name = domainModel.name,
            type = domainModel.type,
            done_dates = domainModel.done_dates,
            color = domainModel.color
        )
    }

    fun mapToEntityList( entity : List <Habit> ) : List<HabitDomainLayer> {
        return entity.map{ mapToEntity(it)}
    }


    fun mapFromEntityList( entity : List<HabitDomainLayer> ) : List<Habit> {
        return entity.map{ mapFromEntity(it)}
    }
}