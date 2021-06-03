package com.example.hubby.presentation.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hubby.databinding.ActivityAddHabitBinding
import com.example.hubby.presentation.viewmodels.HabitViewModel
import com.example.hubby.R
import com.example.data.models.Habit
import com.example.hubby.di.App
import com.google.gson.Gson
import javax.inject.Inject

class ActivityAddHabit : AppCompatActivity()
{
    private lateinit var binding : ActivityAddHabitBinding
    private var viewModel: HabitViewModel? = null
    private var color:Int? = -65536
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(ActivityAddHabit@this)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_add_habit )
        val bundle:Bundle?  = intent.extras

        viewModel = ViewModelProvider(this, viewModelFactory).get(HabitViewModel::class.java)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        if ( bundle?.containsKey("habit_edit") != null) {
            this.title = "Редактирование привычки"
            binding.btnCreate.text = "Сохранить"
            val gson = Gson()
            // TOOD : Error throws here - Haibt class must not be null
            setHabit(gson.fromJson<com.example.data.models.Habit>(intent.getStringExtra("habit_edit"), Habit::class.java))

        }else{
            binding.btnCreate.setOnClickListener {
                // TODO : npe throws here
                viewModel?.insertHabit()
                finish()
            }
        }

        binding.priority.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                viewModel?.setPriority(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    fun pickColor(view: View)
    {
        when(view.id){
            R.id.c1 -> color = ContextCompat.getColor(this,R.color.c1)
            R.id.c2 -> color = ContextCompat.getColor(this,R.color.c2)
            R.id.c3 -> color = ContextCompat.getColor(this,R.color.c3)
            R.id.c4 -> color = ContextCompat.getColor(this,R.color.c4)
            R.id.c5 -> color = ContextCompat.getColor(this,R.color.c5)
            R.id.c6 -> color = ContextCompat.getColor(this,R.color.c6)
            R.id.c7 -> color = ContextCompat.getColor(this,R.color.c7)
            R.id.c8 -> color = ContextCompat.getColor(this,R.color.c8)
            R.id.c9 -> color = ContextCompat.getColor(this,R.color.c9)
            R.id.c10 -> color = ContextCompat.getColor(this,R.color.c10)
            R.id.c11 -> color = ContextCompat.getColor(this,R.color.c11)
            R.id.c12 -> color = ContextCompat.getColor(this,R.color.c12)
            R.id.c13 -> color = ContextCompat.getColor(this,R.color.c13)
            R.id.c14 -> color = ContextCompat.getColor(this,R.color.c14)
            R.id.c15 -> color = ContextCompat.getColor(this,R.color.c15)
            R.id.c16 -> color = ContextCompat.getColor(this,R.color.c16)
        }
        binding.colorPicker.backgroundTintList = ColorStateList.valueOf(color!!)
        viewModel?.setColor(color)
    }

    override fun finish(){
        super.finish()
       // overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    private fun setHabit(habit: com.example.data.models.Habit){
        binding.vm?.name?.value  = habit.name
        binding.vm?.desc?.value = habit.description
        binding.vm?.frequency?.value = habit.frequency.toString()
        binding.vm?.count?.value = habit.count.toString()
        binding.vm?.ch_color?.value = habit.color
        if(habit.type == 0 ) binding.vm?.goodH?.value = true
        else  binding.vm?.badH?.value = true

        binding.btnCreate.setOnClickListener{
                // TODO : npe throws here
                viewModel?.updateHabit(habit.uid)
                finish()
                // Could have been done more beautiful
                /*val intent = Intent(Activity_Done@this, Activity_Done::class.java)
                startActivity(intent)*/
        }
    }


}