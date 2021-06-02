package com.example.domain.interactors.retorfit

import com.example.domain.repositories.Repo
import javax.inject.Inject


class DeleteHabitFromServer @Inject constructor(private val habitRepository : Repo)
{
   suspend operator  fun invoke(uid:HashMap<String,String>) = habitRepository.deleteFromServer(uid)
}