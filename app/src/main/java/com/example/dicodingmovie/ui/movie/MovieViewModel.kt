package com.example.dicodingmovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy

class MovieViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = appRepository.getAllMovies()
}