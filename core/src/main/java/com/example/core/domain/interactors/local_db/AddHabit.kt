package com.example.core.domain.interactors.local_db

import com.example.core.data.HabitRepository
import com.example.core.domain.models.HabitDomainLayer

class AddHabit ( private val habitRepository: HabitRepository )
{
    suspend operator  fun invoke(habit: HabitDomainLayer) = habitRepository.addHabit(habit)
}