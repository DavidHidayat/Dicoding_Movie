package com.example.dicodingmovie.di

import android.content.Context
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.utils.helper.JsonHelper

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AppRepository.getInstance(remoteDataSource)
    }
}