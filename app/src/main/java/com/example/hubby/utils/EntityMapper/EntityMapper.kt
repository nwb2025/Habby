package com.example.hubby.utils.EntityMapper

interface EntityMapper <Entity , DomainModel > {

    fun mapFromEntity( entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel) : Entity

}