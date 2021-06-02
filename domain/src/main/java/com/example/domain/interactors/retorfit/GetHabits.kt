package com.example.domain.interactors.retorfit

import com.example.domain.repositories.Repo
import javax.inject.Inject

class GetHabits   @Inject constructor(private val habitRepository : Repo) {
    operator  fun invoke() = habitRepository.getHabits()
}