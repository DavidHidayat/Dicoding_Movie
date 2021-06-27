package com.example.dicodingmovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.vo.Resource

class MovieViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = appRepository.getAllMovies()
}