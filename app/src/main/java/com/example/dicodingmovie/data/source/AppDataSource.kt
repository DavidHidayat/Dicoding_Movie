package com.example.dicodingmovie.data.source

import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse

interface AppDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovie(movieId: Int): LiveData<MovieEntity>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>
}