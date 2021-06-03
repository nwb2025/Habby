package com.example.hubby.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.hubby.databinding.FragmenthomeBinding
import com.example.hubby.di.App
import com.example.hubby.presentation.adapters.MyViewPagerAdapter
import com.example.hubby.presentation.viewmodels.HabitViewModelForList
import javax.inject.Inject

class HomeFragment : Fragment()
{
    private lateinit var binding : FragmenthomeBinding
    private var sharedViewModel: HabitViewModelForList? = null
    private  lateinit var myContext:MainActivity
    private lateinit var adapter: MyViewPagerAdapter
    private lateinit var goodFragment:FragmentGoodHabits
    private lateinit var badFragment:FragmentBadHabits
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).appComponent.inject(HomeFragment@this)

        sharedViewModel = ViewModelProvider( myContext, viewModelFactory).get(HabitViewModelForList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmenthomeBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        goodFragment = FragmentGoodHabits()
        badFragment = FragmentBadHabits()
        adapter = MyViewPagerAdapter(childFragmentManager)
        adapter.apply {
            addFragment(goodFragment,"Хорошие")
            addFragment(badFragment,"Плохие")
        }
        binding.apply {
            viewPager.adapter = adapter
            tabL.setupWithViewPager(binding.viewPager)
            btnAdd.setOnClickListener() {
                val intent = Intent(context, ActivityAddHabit::class.java)
                startActivity(intent)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                goodFragment.updateRecView(query)
                badFragment.updateRecView(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                goodFragment.updateRecView(query)
                badFragment.updateRecView(query)
                return true
            }
        })
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as MainActivity
        super.onAttach(activity)
    }


}