package com.example.hubby.presentation.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.core.domain.interactors.local_db.DeleteHabit
import com.example.core.domain.interactors.local_db.GetAllHabits
import com.example.core.domain.interactors.local_db.UpdateDoneDates
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.DeleteHabitFromServer
import com.example.core.interactors.retorfit.PostHabit
import com.example.hubby.data.models.Habit
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import kotlinx.coroutines.launch

// here we use use-cases to process the ui actions
class  HabitViewModelForList (private val getHabits : GetAllHabits,
                              private val deleteH: DeleteHabit,
                              private val deleteFromS: DeleteHabitFromServer,
                              private val updateDoneDates: UpdateDoneDates,
                              private val updateDoneOnServer: PostHabit,
                              private val mapper:HabitDBMapper,
                              private val mapperApi:HabitRetrofitMapper)  : ViewModel() , Observable {
    val habitList : LiveData<List<HabitDomainLayer>?> = getHabits().asLiveData()

    var filteredhabits : List<HabitDomainLayer> = mutableListOf()
    // TODO: and here we transform it to the Habit Model

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
    fun getHabitsByName(name:String):List<HabitDomainLayer> {

        return filteredhabits
    }

    fun deleteHabit(habit: Habit)
    {
        viewModelScope.launch {
            deleteH( mapper.mapFromEntity(habit))
            val uid = HashMap<String,String>()
            uid.put("uid",habit.uid)
            deleteFromS(uid)
        }
    }

    fun updateDone(done_dates:ArrayList<Int>, id:String){
        viewModelScope.launch {
            val current_time = (System.currentTimeMillis()/3600000).toInt()
            done_dates.add(current_time)
            // TODO: should be fixed - we need to pass ArrayList and it will be converted futher
            updateDoneDates(done_dates.toString(), id)
            updateDoneOnServer(HabitDone(current_time,id))
        }
    }

}