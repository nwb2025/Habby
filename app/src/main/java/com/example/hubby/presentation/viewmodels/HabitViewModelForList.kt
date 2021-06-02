package com.example.hubby.presentation.viewmodels

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.domain.interactors.local_db.DeleteHabit
import com.example.domain.interactors.local_db.GetAllHabits
import com.example.domain.interactors.local_db.UpdateDoneDates
import com.example.domain.models.HabitDomainLayer
import com.example.domain.models.HabitDone
import com.example.domain.interactors.retorfit.DeleteHabitFromServer
import com.example.domain.interactors.retorfit.PostHabit
import kotlinx.coroutines.launch
import javax.inject.Inject

// here we use use-cases to process the ui actions
class  HabitViewModelForList @Inject constructor (private val getHabits : GetAllHabits,
                              private val deleteH: DeleteHabit,
                              private val deleteFromS: DeleteHabitFromServer,
                              private val updateDoneDates: UpdateDoneDates,
                              private val updateDoneOnServer: PostHabit
)
    : ViewModel() , Observable {
    val habitList : LiveData<List<HabitDomainLayer>> = getHabits().asLiveData()

    var filteredhabits : List<HabitDomainLayer> = mutableListOf()
    // TODO: and here we transform it to the Habit Model

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
    fun getHabitsByName(name:String):List<HabitDomainLayer> {

        return filteredhabits
    }

    fun deleteHabit(habit: HabitDomainLayer) {
        viewModelScope.launch {
            deleteH( habit)
            val uid = HashMap<String,String>()
            uid["uid"] = habit.uid!!
            deleteFromS(uid)
        }
    }

    fun updateDone(done_dates:ArrayList<Int>, id:String){
        viewModelScope.launch {
            val currentTime = (System.currentTimeMillis()/3600000).toInt()
            done_dates.add(currentTime)
            // TODO: should be fixed - we need to pass ArrayList and it will be converted futher
            updateDoneDates(done_dates.toString(), id)
            updateDoneOnServer(HabitDone(currentTime,id))
        }
    }

}