package com.example.dicodingmovie.data.source

import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse

interface AppDataSource {
    fun getAllMovies(): List<MovieEntity>

    fun getMovie(movieId: Int): MovieEntity

    fun getAllTvShow(): List<TvShowEntity>

    fun getTvShow(tvShowId: Int): TvShowEntity
}