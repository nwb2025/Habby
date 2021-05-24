package com.example.core.interactors.local_db

import com.example.core.data.HabitRepository

class GetByType (private val habitRepository: HabitRepository)
{
    operator fun invoke(type:String) = habitRepository.getByType(type)
}