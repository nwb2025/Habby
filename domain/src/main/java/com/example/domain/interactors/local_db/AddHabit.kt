package com.example.domain.interactors.local_db

import com.example.domain.models.HabitDomainLayer
import com.example.domain.repositories.Repo
import javax.inject.Inject


class AddHabit @Inject constructor(private val habitRepository: Repo)
{
    suspend operator  fun invoke(habit: HabitDomainLayer) = habitRepository.addHabit(habit)
}