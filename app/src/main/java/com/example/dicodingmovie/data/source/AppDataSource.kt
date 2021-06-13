package com.example.dicodingmovie.data.source

import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity

interface AppDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getMovie(movieId: Int): LiveData<MovieEntity>
    fun getOthersMovies(movieId: Int): LiveData<List<MovieEntity>>
    fun getAllTvShows(): LiveData<List<TvShowEntity>>
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>
    fun getOthersTvShows(tvShowId: Int): LiveData<List<TvShowEntity>>
}