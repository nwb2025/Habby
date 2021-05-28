package com.example.core.domain.interactors.local_db

import com.example.core.data.HabitRepository

class GetAllHabits ( private val habitRepository:HabitRepository ) {
    operator fun invoke() = habitRepository.getHabits()
}