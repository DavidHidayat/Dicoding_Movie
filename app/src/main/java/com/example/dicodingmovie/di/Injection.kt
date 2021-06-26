package com.example.dicodingmovie.di

import android.content.Context
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.LocalDataSource
import com.example.dicodingmovie.data.source.local.room.AppDatabase
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.utils.AppExecutors
import com.example.dicodingmovie.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AppRepository {
        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()

        return AppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}