package com.example.core.interactors.local_db

import com.example.core.data.HabitRepository
import com.example.core.domain.models.HabitDomainLayer

class DeleteHabit ( private val habitRepository: HabitRepository )
{
    suspend operator fun invoke(habit:HabitDomainLayer) = habitRepository.deleteHabit(habit)
}