package com.example.hubby.presentation.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubby.R
import com.example.hubby.data.models.Habit
import com.example.hubby.presentation.adapters.RecyclerView_Adapter
import com.example.hubby.databinding.FragmentBadHabitsBinding
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.presentation.viewmodels.HabitViewModelForList

class FragmentBadHabits : Fragment() {
    private lateinit var binding : FragmentBadHabitsBinding
    private lateinit var rec_v_adapter: RecyclerView_Adapter
    private val viewModel by activityViewModels <HabitViewModelForList>()
    private val mapper: HabitDBMapper = HabitDBMapper()

    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_bad_habits , container, false)
        initRecView()
        setupObservers()
        // { } is because we pass a function as an argument
        return binding.root
    }
    private fun setupObservers(){
        viewModel.habitList.observe( viewLifecycleOwner, Observer {
            rec_v_adapter.updateHabitList(mapper.mapToEntityList(viewModel.habitList.value?.filter { it.type == 1 }!!) )
        })
    }

    private  fun initRecView() {
        rec_v_adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.habitList.value?.filter { it.type == 1 } ?: listOf()),
                { clickedItem: Habit -> itemClicked(clickedItem)},
                { habit: Habit -> doneListener(habit)  },
                { habit: Habit -> editHabitListener(habit)})
        binding.rvHabits.layoutManager = LinearLayoutManager(context)
        binding.rvHabits.adapter = rec_v_adapter
    }

    // callback
    fun itemClicked(habit: Habit) : Boolean{
        val name = habit.name
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle("Удаление привычки")
                .setMessage("Вы действительно хотите удалить привычку ?")
                .setPositiveButton("Да"){ _, _ ->
            viewModel.deleteHabit(habit)
            Toast.makeText(context, " $name была удалена ", Toast.LENGTH_SHORT).show()
        }.setNegativeButton("Нет"){ _, _ ->
        }.setCancelable(true)
                .create()
                .show()
        return true
    }

    fun doneListener( habit: Habit){
        viewModel.updateDone(habit.done_dates, habit.uid)
        Toast.makeText(context, " ${habit.uid} was uodated  ",Toast.LENGTH_SHORT).show()
    }

    fun editHabitListener(habit: Habit) {
        val intent = Intent(activity, ActivityAddHabit::class.java)
        intent.putExtra("name",habit.name)
        intent.putExtra("desc", habit.description)
        startActivity(intent)
    }

    fun updateRecView(s:String?){
        initRecView()
        rec_v_adapter.filter.filter(s)
    }
}

