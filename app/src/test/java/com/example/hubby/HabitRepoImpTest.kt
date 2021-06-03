package com.example.hubby

import com.example.data.LocalDataDataSource
import com.example.data.RemoteDataDataSource
import com.example.data.mappers.HabitDBMapper
import com.example.data.mappers.HabitRetrofitMapper
import com.example.data.models.Habit
import com.example.data.repo.HabitRepositoryImp
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HabitRepoImpTest {

    // Test subject
    lateinit var habitRepository : HabitRepositoryImp

    // Collaborators
    lateinit var localDataSource : LocalDataDataSource
    lateinit var remoteDataDataSource: RemoteDataDataSource
    lateinit var dbMapper : HabitDBMapper
    lateinit var retrofitMapper : HabitRetrofitMapper

    // Utils
    lateinit var habitFromDao : Habit
    lateinit var habitfromApi : Habit

    @Before
    fun setUp(){
        localDataSource = mock()
        remoteDataDataSource = mock()
        dbMapper = mock()
        retrofitMapper = mock()
        habitRepository = HabitRepositoryImp(localDataSource,remoteDataDataSource,dbMapper,retrofitMapper)

    }



}