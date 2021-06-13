package com.example.dicodingmovie.data.source.remote

import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
import com.example.dicodingmovie.utils.helper.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovies()
    fun getMovie(movieId:Int): MovieResponse? = jsonHelper.loadMovie(movieId)
    fun getOtherMovies(movieId:Int): List<MovieResponse> = jsonHelper.loadOtherMovies(movieId)

    fun getAllTvShow(): List<TvShowResponse> = jsonHelper.loadTvShows()
    fun getTvShow(tvShowId:Int): TvShowResponse? = jsonHelper.loadTvShow(tvShowId)
    fun getOtherTvShows(tvShowId:Int): List<TvShowResponse> = jsonHelper.loadOtherTvShows(tvShowId)
}