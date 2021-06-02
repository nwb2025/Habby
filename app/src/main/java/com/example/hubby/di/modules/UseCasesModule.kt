package com.example.hubby.di.modules

import com.example.domain.interactors.local_db.*
import com.example.domain.interactors.retorfit.DeleteHabitFromServer
import com.example.domain.interactors.retorfit.GetHabits
import com.example.domain.interactors.retorfit.PostHabit
import com.example.domain.interactors.retorfit.PutHabit
import com.example.domain.repositories.Repo
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideAddHabit(repo:Repo): AddHabit{
         return AddHabit(repo )
    }

    @Provides
    fun provideDeleteHabit(repo: Repo):DeleteHabit{
        return DeleteHabit(repo)
    }

    @Provides
    fun provideGetAllHabits(repo: Repo):GetAllHabits{
        return GetAllHabits(repo)
    }

    @Provides
    fun provideUpdateDoneDates(repo: Repo):UpdateDoneDates{
        return UpdateDoneDates(repo)
    }

    @Provides
    fun provideUpdateHabit(repo: Repo): Updatehabit{
        return Updatehabit(repo)
    }

    // remote use cases
    @Provides
    fun provideDeleteFromServer(repo: Repo):DeleteHabitFromServer{
        return DeleteHabitFromServer(repo)
    }


    @Provides
    fun providePostHabit(repo: Repo):PostHabit{
        return PostHabit(repo)
    }

    @Provides
    fun providePutHabit(repo: Repo):PutHabit{
        return PutHabit(repo)
    }
}