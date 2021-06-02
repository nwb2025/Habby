package com.example.hubby.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.local_db.*
import com.example.domain.interactors.retorfit.DeleteHabitFromServer
import com.example.domain.interactors.retorfit.PostHabit
import com.example.domain.interactors.retorfit.PutHabit
import com.example.hubby.presentation.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideViewModelFactory(getAllHabits: GetAllHabits,
                       addHabit: AddHabit,
                       deleteHabit: DeleteHabit,
                       deleteHabitFromServer: DeleteHabitFromServer,
                       putHabit: PutHabit,
                       updateDoneDates: UpdateDoneDates,
                       postHabit: PostHabit,
                       updateHabit: Updatehabit): ViewModelProvider.Factory{

        return ViewModelFactory(
            getAllHabits,
            addHabit,
            deleteHabit,
            deleteHabitFromServer,
            putHabit,
            updateDoneDates,
            postHabit,
            updateHabit)
    }
}