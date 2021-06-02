package com.example.domain.interactors.local_db

import com.example.domain.repositories.Repo
import javax.inject.Inject


class UpdateDoneDates @Inject constructor( private  val habitRepository : Repo) {

    suspend operator fun invoke(doneDates:String, uid:String) = habitRepository.updateDoneDates(doneDates, uid)
}