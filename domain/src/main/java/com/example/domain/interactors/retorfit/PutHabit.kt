package com.example.domain.interactors.retorfit

import com.example.domain.models.HabitDomainLayer
import com.example.domain.repositories.Repo
import javax.inject.Inject

class PutHabit @Inject constructor(private val habitRepository : Repo) {
    suspend operator fun invoke(habit: HabitDomainLayer) = habitRepository.put( habit )
}