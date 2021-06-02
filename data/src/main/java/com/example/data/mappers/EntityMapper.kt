package com.example.data.mappers

interface EntityMapper <Entity , DomainModel > {
    fun mapToEntity(domainModel : DomainModel) : Entity
    fun mapFromEntity( entity : Entity): DomainModel
}