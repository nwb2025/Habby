package com.example.hubby.di.modules

import android.app.Application
import android.content.Context
import com.example.data.LocalDataDataSource
import com.example.data.RemoteDataDataSource
import com.example.data.db.Dao
import com.example.data.mappers.EntityMapper
import com.example.data.mappers.HabitDBMapper
import com.example.data.mappers.HabitRetrofitMapper
import com.example.data.models.Habit
import com.example.data.models.HabitResponse
import com.example.data.repo.HabitRepositoryImp
import com.example.domain.models.HabitDomainLayer
import com.example.domain.repositories.Repo
import com.example.hubby.frameworks.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// an entity that contains all the dependencies
@Module
class RepositoryModule( private val app: Context) {

    @Provides
    @Singleton
    fun provideContext() : Context {
        return app
    }

    @Provides
    fun provideAppDataBase(context: Context) : AppDataBase{
        return AppDataBase.getAppDB(context)
    }

    @Provides
    fun provideDao(appDataBase: AppDataBase): Dao {
        return appDataBase.habitDao()
    }

    @Provides
    fun provideLocalDataSource(dao : Dao) : LocalDataDataSource {
        return RoomHabitDataSource(dao)
    }

    @Provides
    fun provideRemoteDataSource(retroApi: RetrofitInstance) : RemoteDataDataSource{
        return RetrofitHabitDataSource(retroApi)
    }

    @Provides
    fun provideRetrofitInstance() : RetrofitInstance{
        return RetrofitInstance
    }

    @Provides
    fun provideLocalMapper():HabitDBMapper{
         return HabitDBMapper()
    }

    @Provides
    fun provideRemoteMapper(): HabitRetrofitMapper{
        return HabitRetrofitMapper()
    }

    @Provides
    @Singleton
    fun provideRepository(localDataDataSource: LocalDataDataSource,
                   remoteDataDataSource: RemoteDataDataSource,
                   dbMapper: HabitDBMapper,
                   remoteMapper: HabitRetrofitMapper): Repo {
        return HabitRepositoryImp(localDataDataSource, remoteDataDataSource,dbMapper,remoteMapper)
    }
}