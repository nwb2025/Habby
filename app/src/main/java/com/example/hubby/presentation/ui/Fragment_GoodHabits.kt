package com.example.hubby.presentation.ui

import android.app.AlertDialog
import android.content.Context
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
import com.example.hubby.data.db.Habit
import com.example.hubby.databinding.FragmentGoodHabitsBinding
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.presentation.adapters.RecyclerView_Adapter
import com.example.hubby.presentation.viewmodels.HabitViewModelForList
import com.google.gson.Gson


class Fragment_GoodHabits : Fragment()
{
    private lateinit var binding : FragmentGoodHabitsBinding
    private lateinit var rec_v_adapter: RecyclerView_Adapter
    private val viewModel by activityViewModels <HabitViewModelForList>()
    private val mapper:HabitDBMapper = HabitDBMapper()


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment__good_habits, container, false)

        initRecView()

        viewModel.good_habits?.observe( viewLifecycleOwner, Observer {

          binding.rvHabits.adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.good_habits?.value ?: listOf()) ,
                  {habit: Habit ->  itemClicked(habit) },
                  { habit:Habit -> doneListener(habit)  },
                  { habit:Habit -> editHabitListener(habit)} )
       })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }




   private  fun initRecView()
    {
        rec_v_adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.good_habits?.value ?: listOf() ),
                { habit: Habit ->  itemClicked(habit) },
                { habit:Habit -> doneListener(habit) },
                {habit:Habit -> editHabitListener(habit)})
        binding.rvHabits.layoutManager = LinearLayoutManager(context)
        binding.rvHabits.adapter = rec_v_adapter
    }

    //callback

    fun itemClicked(habit: Habit) : Boolean{
        val name = habit.name
        val dialog:AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle("Удаление привычки").setMessage("Вы действительно хотите удалить привычку ?")
        dialog.setPositiveButton("Да"){dialogInterface, which ->
            viewModel.deleteHabit(habit)
            Toast.makeText(context, " $name была удалена ", Toast.LENGTH_SHORT).show()
        }.setNegativeButton("Нет"){dialogInterface, which ->
        }.setCancelable(true).create()

        dialog.show()

        return true
    }

    fun editHabitListener(habit:Habit) {
        val intent = Intent(activity, Activity_AddHabit::class.java)
        val gson: Gson = Gson()
        intent.putExtra("habit_edit", gson.toJson(habit))
        startActivity(intent)
    }

    fun doneListener( habit: Habit) {
        viewModel.updateDone(habit.done_dates, habit.uid)

        Toast.makeText(context, " ${habit.uid} успешно обновлена   ",Toast.LENGTH_SHORT).show()
    }
}