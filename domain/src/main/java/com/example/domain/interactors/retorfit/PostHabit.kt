package com.example.domain.interactors.retorfit

import com.example.domain.models.HabitDone
import com.example.domain.repositories.Repo
import javax.inject.Inject


class PostHabit @Inject constructor( private val habitRepository : Repo) {
    suspend operator  fun invoke(habitDone: HabitDone) = habitRepository.postDone( habitDone )
}