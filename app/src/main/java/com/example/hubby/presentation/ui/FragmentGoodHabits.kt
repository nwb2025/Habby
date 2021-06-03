package com.example.hubby.presentation.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubby.R
import com.example.hubby.databinding.FragmentGoodHabitsBinding
import com.example.domain.models.HabitDomainLayer
import com.example.hubby.presentation.adapters.RecyclerView_Adapter
import com.example.hubby.presentation.viewmodels.HabitViewModelForList
import com.google.gson.Gson


class FragmentGoodHabits : Fragment()
{
    private lateinit var binding : FragmentGoodHabitsBinding
    private lateinit var rec_v_adapter: RecyclerView_Adapter
    private val viewModel by activityViewModels <HabitViewModelForList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment__good_habits, container, false)
        setUpAdapter()
        setupObservers()
        return binding.root
    }

    private fun setupObservers(){
        viewModel.habitList.observe( viewLifecycleOwner, Observer {
            rec_v_adapter.updateHabitList(viewModel.habitList.value?.filter { it.type == 0 }!! )
        })

    }

    private  fun setUpAdapter(){
        rec_v_adapter = RecyclerView_Adapter(viewModel.habitList.value?.filter { it.type == 0 } ?: listOf() ,
                { habit: HabitDomainLayer ->  itemClicked(habit) },
                { habit: HabitDomainLayer -> doneListener(habit) },
                { habit: HabitDomainLayer -> editHabitListener(habit)})
        binding.rvHabits.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rec_v_adapter
        }
    }

    fun updateRecView(s:String?){
        setUpAdapter()
        rec_v_adapter.filter.filter(s)
    }

    fun itemClicked(habit: HabitDomainLayer) : Boolean{
        val name = habit.name
        val dialog:AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle("Удаление привычки").setMessage("Вы действительно хотите удалить привычку ?")
        dialog.setPositiveButton("Да"){ _, _ ->
            viewModel.deleteHabit(habit)
            Toast.makeText(context, " $name была удалена ", Toast.LENGTH_SHORT).show()
        }.setNegativeButton("Нет"){ _, _ ->
        }.setCancelable(true).create()

        dialog.show()
        return true
    }

    fun editHabitListener(habit : HabitDomainLayer) {
        val intent = Intent(activity, ActivityAddHabit::class.java)
        val gson: Gson = Gson()
        intent.putExtra("habit_edit", gson.toJson(habit))
        startActivity(intent)
    }

    fun doneListener( habit: HabitDomainLayer) {
        viewModel.updateDone(habit.done_dates, habit.uid!!)
        Toast.makeText(context, " ${habit.uid} успешно обновлена   ",Toast.LENGTH_SHORT).show()
    }
}