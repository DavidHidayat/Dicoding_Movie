package com.example.dicodingmovie.data.source

import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity

interface AppDataSource {

    fun getAllMovies(): List<MovieEntity>
    fun getMovie(movieId: Int): MovieEntity
    fun getOthersMovies(movieId: Int): List<MovieEntity>
    fun getAllTvShows(): List<TvShowEntity>
    fun getTvShow(tvShowId: Int): TvShowEntity
    fun getOthersTvShows(tvShowId: Int): List<TvShowEntity>
}