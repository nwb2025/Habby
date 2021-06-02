package com.example.data.mappers

import com.example.domain.models.HabitDomainLayer
import com.example.data.models.HabitResponse
import javax.inject.Inject


class HabitRetrofitMapper  :
   EntityMapper<HabitDomainLayer,HabitResponse>
{
    override fun mapToEntity(entity: HabitResponse): HabitDomainLayer {
        return HabitDomainLayer(
            uid = entity.uid,
            name = entity.title,
            count =  entity.count,
            date = entity.date,
            description = entity.description,
            frequency = entity.frequency,
            priority = entity.priority,
            type = entity.type,
            color = entity.color,
            done_dates = entity.done_dates
        )
    }

    override fun mapFromEntity(domainModel: HabitDomainLayer): HabitResponse {
        return HabitResponse(
            uid = domainModel.uid,
            count = domainModel.count,
            date = domainModel.date,
            description = domainModel.description,
            frequency = domainModel.frequency,
            priority = domainModel.priority,
            title = domainModel.name,
            type = domainModel.type,
            done_dates = domainModel.done_dates,
            color = domainModel.color
        )
    }

    fun mapToEntityList( entity : List<HabitResponse> ) : List<HabitDomainLayer> {
        return entity.map{ mapToEntity(it)}
    }

    fun mapFromEntityList( entity : List<HabitDomainLayer> ) : List<HabitResponse> {
        return entity.map{ mapFromEntity(it)}
    }
}