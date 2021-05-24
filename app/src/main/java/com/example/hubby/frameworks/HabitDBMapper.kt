package com.example.hubby.frameworks

import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.db.Habit
import com.example.hubby.utils.EntityMapper.EntityMapper

class HabitDBMapper : EntityMapper <Habit , HabitDomainLayer>
{
    override fun mapFromEntity(entity: Habit): HabitDomainLayer {
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

    override fun mapToEntity(domainModel: HabitDomainLayer): Habit {
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

    fun fromEntityList( entity : List <Habit> ) : List<HabitDomainLayer> {
        return entity.map{ mapFromEntity(it)}
    }


    fun mapToEntityList( entity : List <HabitDomainLayer> ) : List<Habit> {
        return entity.map{ mapToEntity(it)}
    }
}