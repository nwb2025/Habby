package com.example.core.interactors.retorfit

import com.example.core.data.HabitRepository
import com.example.core.domain.models.HabitDomainLayer

class PutHabit (private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habit: HabitDomainLayer) = habitRepository.put( habit )
}