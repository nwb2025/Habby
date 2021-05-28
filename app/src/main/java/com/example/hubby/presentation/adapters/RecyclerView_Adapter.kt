package com.example.hubby.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hubby.R
import com.example.hubby.databinding.RecViewLayoutBinding
import com.example.hubby.data.models.Habit

class RecyclerView_Adapter (private val habitList:List<Habit>?,
                            private val clickListener:(Habit) -> Boolean,
                            private val doneListener: (Habit) -> Unit,
                            private val editListener: (Habit) -> Unit) :
        RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>(),
        Filterable
{
    var habitFilteredList = mutableListOf<Habit>()

    init {
        habitFilteredList = habitList!!.toMutableList()
    }

    inner class ViewHolder(val binding:RecViewLayoutBinding) : RecyclerView.ViewHolder( binding.root ) {
        fun bind(habit: Habit?,
                 clickListener:(Habit) -> Boolean)
        {
            binding.apply {
                habitName.text = habit?.name
                duration.text = habit?.count.toString() + " раза в " + habit?.frequency + " дня"
            }

            binding.color.setBackgroundColor(habit?.color!!)
            setCompleted(binding, habit)

            binding.completed.setOnClickListener {
                if ( habit.done_dates.isEmpty() || ((  (System.currentTimeMillis()/3600000) - habit?.done_dates.last().toLong())   >= ((habit.count*24)/habit.frequency) )){
                    doneListener(habit)
                    binding.completed.isActivated = true
                }
            }
            binding.cardview.setOnClickListener{
                editListener(habit)
            }
            binding.cardview.setOnLongClickListener( View.OnLongClickListener {
                clickListener(habit)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:RecViewLayoutBinding = DataBindingUtil.inflate(inflater,R.layout.rec_view_layout,parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = habitFilteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(habitFilteredList[position], clickListener)

    private fun setCompleted(binding:RecViewLayoutBinding, habit: Habit?){
        binding.completed.isChecked = !(habit?.done_dates?.isEmpty()!!  ||  ( (System.currentTimeMillis()/3600000 - habit.done_dates.last().toLong())   >= ((habit.count*24)/habit.frequency) ))
    }

    fun updateHabitList(habitList:List<Habit>){
        this.habitFilteredList = habitList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                habitFilteredList = if (charSearch.isEmpty()) {
                    habitList!!.toMutableList<Habit>()
                } else {
                    val resultList = mutableListOf<Habit>()
                    for (row in habitList!!) {
                        if (row.name.toLowerCase().contains(charSearch,true)) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = habitFilteredList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                habitFilteredList = results!!.values as MutableList<Habit>
                notifyDataSetChanged()
            }
        }
    }

}