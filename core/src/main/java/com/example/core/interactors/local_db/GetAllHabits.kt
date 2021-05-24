package com.example.core.interactors.local_db

import com.example.core.data.HabitRepository

class GetAllHabits ( private val habitRepository:HabitRepository )
{
    operator fun invoke() = habitRepository.getAll()
}