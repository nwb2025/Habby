package com.example.hubby.presentation.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.domain.interactors.local_db.AddHabit
import com.example.domain.interactors.local_db.UpdateDoneDates
import com.example.domain.interactors.local_db.Updatehabit
import com.example.domain.models.HabitDomainLayer
import com.example.domain.interactors.retorfit.PostHabit
import com.example.domain.interactors.retorfit.PutHabit
import kotlinx.coroutines.launch
import javax.inject.Inject

// here we use use-cases to process the ui actions
class  HabitViewModel  @Inject constructor(
    private  val addHabit : AddHabit,
    private val putHabit : PutHabit,
    private val updateDoneDates : UpdateDoneDates,
    private val updateDoneOnServer : PostHabit,
    private val updateHabit : Updatehabit
)  : ViewModel() , Observable {

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
    val frequency = MutableLiveData<String>().apply { value = "1"}
    @Bindable
    val ch_color = MutableLiveData<Int>().apply { value = 0 }
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
            val color = ch_color.value!!
            when {
                goodH.value  == true -> {
                    val result = putHabit(HabitDomainLayer(null,count!!,date,des,freq!!,priority,n,0,color, done_dates))
                    addHabit(
                        HabitDomainLayer(
                            uid = result.uid,
                            count = count,
                            date =date,
                            description = des,
                            frequency = freq,
                            priority = priority,
                            name = n,
                            type = 0,
                            done_dates = done_dates,
                            color = color
                        )
                    )
                }
                badH.value == true -> {
                    val result = putHabit(HabitDomainLayer(null,count!!,date,des,freq!!,priority,n,1,color, done_dates))
                    addHabit(
                        HabitDomainLayer(
                            uid = result.uid,
                            count = count,
                            date =date,
                            description = des,
                            frequency = freq,
                            priority = priority,
                            name = n,
                            type = 1,
                            done_dates = done_dates,
                            color = color
                        )
                    )
                }
            }
        }

        name.value = null
        desc.value = null
        badH.value = null
        goodH.value = null
        frequency.value = null
        count.value = null
        ch_color.value = 0
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
            val color = ch_color.value!!
            when {
                goodH.value  == true -> {
                    //putHabit(HabitDomainLayer(uid,count!!,date,des,freq!!,priority,n,0,col, done_dates))
                    updateHabit(
                        HabitDomainLayer(
                            uid = uid,
                            count = count,
                            date = date,
                            description = des,
                            frequency = freq,
                            priority = priority,
                            name = n,
                            type = 0 ,
                            done_dates = done_dates,
                            color = color))
                }
                badH.value == true -> {
                    //putHabit(HabitDomainLayer(uid,count!!,date,des,freq!!,priority,n,1,col, done_dates))
                    updateHabit(
                        HabitDomainLayer(
                        uid = uid,
                        count = count,
                        date = date,
                        description = des,
                        frequency = freq,
                        priority = priority,
                        name = n,
                        type = 1 ,
                        done_dates = done_dates,
                        color = color))
                }
            }
        }

        name.value = null
        desc.value = null
        badH.value = null
        goodH.value = null
        frequency.value = "1"
        count.value = null
        ch_color.value = 0
    }

    fun setColor(color:Int?){
        ch_color.apply { value = color }
    }

    fun setPriority(pr:Int){
        priority = pr
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}