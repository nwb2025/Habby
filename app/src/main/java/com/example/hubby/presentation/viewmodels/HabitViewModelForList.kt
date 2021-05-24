package com.example.hubby.presentation.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.domain.models.HabitDone
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.DeleteHabitFromServer
import com.example.core.interactors.retorfit.PostHabit
import com.example.hubby.data.db.Habit
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import kotlinx.coroutines.launch

// here we use use-cases to process the ui actions
class  HabitViewModelForList (private val getHabits:GetAllHabits,
                              private val getHabitsByType: GetByType,
                              private val deleteH: DeleteHabit,
                              private val deleteFromS: DeleteHabitFromServer,
                              private val updateDoneDates: UpdateDoneDates,
                              private val updateDoneOnServer: PostHabit,
                              private val mapper:HabitDBMapper,
                              private val mapperApi:HabitRetrofitMapper)  : ViewModel() , Observable {
    val good_habits : LiveData<List<HabitDomainLayer>?> = getHabitsByType("0").asLiveData()
    val bad_habits : LiveData<List<HabitDomainLayer>?>  = getHabitsByType("1").asLiveData()
    // TODO: and here we transform it to the Habit Model

    // From DB ROOM
    val habits: LiveData<List<HabitDomainLayer>> = getHabits().asLiveData()
   // val habits : LiveData<List<HabitDomainLayer>> = getHabitsFromServer().asLiveData()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

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