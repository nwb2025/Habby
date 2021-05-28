package com.example.hubby.presentation.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.core.domain.interactors.local_db.AddHabit
import com.example.core.domain.interactors.local_db.UpdateDoneDates
import com.example.core.domain.interactors.local_db.Updatehabit
import com.example.core.domain.models.HabitDomainLayer
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.PostHabit
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.data.models.Habit
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import kotlinx.coroutines.launch

// here we use use-cases to process the ui actions
class  HabitViewModel (
        private  val addHabit : AddHabit,
        private val putHabit : PutHabit,
        private val updateDoneDates : UpdateDoneDates,
        private val updateDoneOnServer : PostHabit,
        private val updateHabit : Updatehabit,
        private val mapper : HabitDBMapper,
        private val mapperApi : HabitRetrofitMapper)  : ViewModel() , Observable {

    @Bindable
    val name = MutableLiveData<String>()
    @Bindable
    val desc = MutableLiveData<String>()
    @Bindable
    val goodH = MutableLiveData<Boolean>().apply { value = true }
    @Bindable
    val badH = MutableLiveData<Boolean>()
    @Bindable
    val count = MutableLiveData<String>()
    @Bindable
    val frequency = MutableLiveData<String>()
    @Bindable
    val ch_color = MutableLiveData<Int>()
    private var col:Int = -65536
    private var priority:Int = 0


    fun insertHabit()
    {
        viewModelScope.launch {
            val des = desc.value.toString() // description
            val n = name.value.toString() // name
            val date  =  (System.currentTimeMillis() / 3600000).toInt() // date
            val freq = frequency.value?.toInt()
            val count =  count.value?.toInt()
            val done_dates = ArrayList<Int>()
            when {
                goodH.value  == true -> {
                    val result = putHabit(HabitDomainLayer(null,count!!,date,des,freq!!,priority,n,0,col, done_dates))
                    addHabit(mapper.mapFromEntity(Habit(result.uid, count, date, des, freq, priority, n, 0, done_dates, col)))
                }
                badH.value == true -> {
                    val result = putHabit(HabitDomainLayer(null,count!!,date,des,freq!!,priority,n,1,col, done_dates))
                    addHabit(mapper.mapFromEntity(Habit(result.uid, count, date, des, freq, priority, n, 1 , done_dates, col)))
                }
            }
        }

        name.value = null
        desc.value = null
        badH.value = null
        goodH.value = null
        frequency.value = null
        count.value = null
    }

    fun updateHabit(uid:String){
        viewModelScope.launch {
            val des = desc.value.toString() // description
            val n = name.value.toString() // name
            val date  =  (System.currentTimeMillis() / 3600000).toInt() // date

            // TODO: must be fixed !
            val freq = frequency.value!!.toInt()
            val count =  count.value!!.toInt()
            val done_dates = ArrayList<Int>()
           /* when {
                goodH.value  == true -> {
                    putHabit(HabitDomainLayer(uid,count!!,date,des,freq!!,priority,n,0,col, done_dates))
                    updateHabit(mapper.mapFromEntity(Habit(uid, count, date, des, freq, priority, n, 0, done_dates, col)))
                }
                badH.value == true -> {
                    putHabit(HabitDomainLayer(uid,count!!,date,des,freq!!,priority,n,1,col, done_dates))
                    updateHabit(mapper.mapFromEntity(Habit(uid, count, date, des, freq, priority, n, 1 , done_dates, col)))
                }
                else -> Log.i("Error", "You must specify the habit type!")
            }*/
        }

        name.value = null
        desc.value = null
        badH.value = null
        goodH.value = null
        frequency.value = null
        count.value = null
    }

    fun setColor(color:Int?){
        if (color != null)
            col = color
    }

    fun setPriority(pr:Int){
        priority = pr
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}