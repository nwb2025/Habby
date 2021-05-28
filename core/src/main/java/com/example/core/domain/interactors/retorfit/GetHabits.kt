package com.example.core.interactors.retorfit

import com.example.core.data.HabitRepository

class GetHabits  (private val habitRepository: HabitRepository) {
    operator  fun invoke() = habitRepository.getHabits()
}