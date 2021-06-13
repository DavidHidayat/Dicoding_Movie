package com.example.dicodingmovie.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
import com.example.dicodingmovie.utils.helper.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback){
        handler.postDelayed({ callback.onAllMoviesReceived(jsonHelper.loadMovies()) }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getMovie(movieId:Int ,callback: LoadMovieCallback){
        handler.postDelayed({ callback.onMovieReceived(jsonHelper.loadMovie(movieId)) }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getOtherMovies(movieId:Int,callback: LoadOthersMoviesCallback){
        handler.postDelayed({ callback.onOthersMoviesReceived(jsonHelper.loadOtherMovies(movieId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShow(callback: LoadTvShowsCallback){
        handler.postDelayed({ callback.onAllTvShowsReceived(jsonHelper.loadTvShows()) }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getTvShow(tvShowId:Int,callback: LoadTvShowCallback){
        handler.postDelayed({ callback.onTvShowReceived(jsonHelper.loadTvShow(tvShowId)) }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getOtherTvShows(tvShowId:Int,callback: LoadOthersTvShowsCallback){
        handler.postDelayed({ callback.onOthersTvShowsReceived(jsonHelper.loadOtherTvShows(tvShowId)) }, SERVICE_LATENCY_IN_MILLIS)

    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }
    interface LoadMovieCallback {
        fun onMovieReceived(movieResponse: MovieResponse?)
    }
    interface LoadOthersMoviesCallback {
        fun onOthersMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }
    interface LoadTvShowCallback {
        fun onTvShowReceived(tvShowResponse: TvShowResponse?)
    }
    interface LoadOthersTvShowsCallback {
        fun onOthersTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

}