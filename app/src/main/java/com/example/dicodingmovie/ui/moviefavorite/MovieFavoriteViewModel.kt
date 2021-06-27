package com.example.dicodingmovie.ui.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.vo.Resource

class MovieFavoriteViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getMovies(): LiveData<List<MovieFavoriteEntity>> = appRepository.getFavoritedMovie()
}