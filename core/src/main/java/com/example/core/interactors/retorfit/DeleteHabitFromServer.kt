package com.example.core.interactors.retorfit

import com.example.core.data.HabitRepository

class DeleteHabitFromServer (private val habitRepository: HabitRepository)
{
   suspend operator  fun invoke(uid:HashMap<String,String>) = habitRepository.deleteFromServer(uid)
}