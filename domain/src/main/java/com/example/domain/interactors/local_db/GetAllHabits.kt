package com.example.domain.interactors.local_db

import com.example.domain.repositories.Repo
import javax.inject.Inject

class GetAllHabits  @Inject constructor( private val habitRepository : Repo)  {
    operator fun invoke() = habitRepository.getHabits()
}