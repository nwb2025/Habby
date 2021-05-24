package com.example.core.interactors.retorfit

import com.example.core.data.HabitRepository
import com.example.core.domain.models.HabitDone


class PostHabit ( private val habitRepository: HabitRepository) {
    suspend operator  fun invoke(habitDone: HabitDone) = habitRepository.postDone( habitDone )
}