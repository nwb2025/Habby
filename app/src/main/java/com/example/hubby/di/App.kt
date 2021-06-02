package com.example.hubby.di

import android.app.Application
import com.example.hubby.di.modules.RepositoryModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
       appComponent = initDagger(this)
    }

   private fun initDagger(app : App) : AppComponent {
        return  DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule(applicationContext))
            .build()
   }

}