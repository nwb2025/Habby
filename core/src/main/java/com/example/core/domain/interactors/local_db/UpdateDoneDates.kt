package com.example.core.domain.interactors.local_db

import com.example.core.data.HabitRepository

class UpdateDoneDates ( private  val habitRepository: HabitRepository) {

    suspend operator fun invoke(doneDates:String, uid:String) = habitRepository.updateDoneDates(doneDates, uid)
}