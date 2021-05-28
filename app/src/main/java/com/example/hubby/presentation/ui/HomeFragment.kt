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
import com.example.core.data.HabitRepository
import com.example.core.data.LocalDataDataSource
import com.example.core.data.RemoteDataDataSource
import com.example.core.domain.interactors.local_db.AddHabit
import com.example.core.domain.interactors.local_db.DeleteHabit
import com.example.core.domain.interactors.local_db.GetAllHabits
import com.example.core.domain.interactors.local_db.UpdateDoneDates
import com.example.core.domain.interactors.local_db.Updatehabit
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.DeleteHabitFromServer
import com.example.core.interactors.retorfit.PostHabit
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.data.api.RetrofitInstance
import com.example.hubby.data.db.AppDataBase
import com.example.hubby.data.db.Dao
import com.example.hubby.databinding.FragmenthomeBinding
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import com.example.hubby.frameworks.RetrofitHabitDataSource
import com.example.hubby.frameworks.RoomHabitDataSource
import com.example.hubby.presentation.adapters.MyViewPagerAdapter
import com.example.hubby.presentation.viewmodels.HabitViewModelForList
import com.example.hubby.presentation.viewmodels.ViewModelFactory


class HomeFragment : Fragment()
{
    private lateinit var binding : FragmenthomeBinding
    private var db : AppDataBase? = null
    private var hDao : Dao? = null
    private var localDataSource: RoomHabitDataSource? = null
    private var remoteDataSource : RetrofitHabitDataSource? = null
    private var sharedViewModel: HabitViewModelForList? = null
    private var repo: HabitRepository? = null
    private var repoRetrofit : HabitRepository? = null
    private  lateinit var myContext:MainActivity
    private lateinit var adapter: MyViewPagerAdapter
    private lateinit var goodFragment:FragmentGoodHabits
    private lateinit var badFragment:FragmentBadHabits

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDataBase.getAppDB( context = myContext )
        hDao = db?.habitDao()
        localDataSource = RoomHabitDataSource(hDao)
        remoteDataSource = RetrofitHabitDataSource(RetrofitInstance)
        repo = HabitRepository(
                localDataSource as LocalDataDataSource,
                remoteDataSource as RemoteDataDataSource)

        val factory = ViewModelFactory(
            GetAllHabits(repo as HabitRepository),
            AddHabit(repo as HabitRepository),
            DeleteHabit(repo as HabitRepository),
            DeleteHabitFromServer(repo as HabitRepository),
            PutHabit(repo as HabitRepository),
            UpdateDoneDates(repo as HabitRepository),
            PostHabit(repo as HabitRepository),
            Updatehabit(repo as HabitRepository),
            HabitDBMapper() ,
            HabitRetrofitMapper() )

        sharedViewModel = ViewModelProvider( myContext, factory).get(HabitViewModelForList::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmenthomeBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews()
    {
        // TODO: should be fixed
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