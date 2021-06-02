package com.example.hubby.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.local_db.AddHabit
import com.example.domain.interactors.local_db.DeleteHabit
import com.example.domain.interactors.local_db.GetAllHabits
import com.example.domain.interactors.local_db.UpdateDoneDates
import com.example.domain.interactors.local_db.Updatehabit
import com.example.domain.interactors.retorfit.DeleteHabitFromServer
import com.example.domain.interactors.retorfit.PostHabit
import com.example.domain.interactors.retorfit.PutHabit
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val getHabits: GetAllHabits,
                        private  val addHabit: AddHabit,
                        private val deleteH: DeleteHabit,
                        private val deleteFromS: DeleteHabitFromServer,
                        private val putHabit: PutHabit,
                        private val updateDoneDates: UpdateDoneDates,
                        private val updateDoneOnS: PostHabit,
                        private val updateHabit: Updatehabit
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(HabitViewModel::class.java )) {
            return  HabitViewModel(
                addHabit,
                putHabit,
                updateDoneDates,
                updateDoneOnS,
                updateHabit
            ) as T

        }
        else if(modelClass.isAssignableFrom(HabitViewModelForList::class.java)){
            return HabitViewModelForList(
                getHabits,
                deleteH,
                deleteFromS,
                updateDoneDates,
                updateDoneOnS ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}