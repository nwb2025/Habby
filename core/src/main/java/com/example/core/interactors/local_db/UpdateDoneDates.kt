package com.example.core.interactors.local_db

import com.example.core.data.HabitRepository
import com.example.core.domain.models.HabitDomainLayer

class UpdateDoneDates ( private  val habitRepository: HabitRepository) {

    suspend operator fun invoke(doneDates:String, uid:String) = habitRepository.updateDoneDates(doneDates, uid)
}