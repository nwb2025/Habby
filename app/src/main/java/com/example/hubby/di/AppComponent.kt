package com.example.hubby.di

import com.example.hubby.di.modules.RepositoryModule
import com.example.hubby.di.modules.UseCasesModule
import com.example.hubby.di.modules.ViewModelModule
import com.example.hubby.presentation.ui.HomeFragment
import dagger.Component
import javax.inject.Singleton


@Component ( modules = [RepositoryModule::class,UseCasesModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    // dagger convention - in args - specify a class in which we're going to pass dependencies
    fun inject(target: HomeFragment)
}