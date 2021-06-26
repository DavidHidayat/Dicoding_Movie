package com.example.dicodingmovie.data.source.local

import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.room.AppDao

class LocalDataSource private constructor(private val mAppDao: AppDao)  {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mAppDao.getMovies()
    fun getOthersMovies(movieId: Int?): LiveData<List<MovieEntity>> = mAppDao.getOthersMovies(movieId)
    fun getMovieById(movieId:Int): LiveData<MovieEntity> = mAppDao.getMovieById(movieId)
    fun insertMovies(movies: List<MovieEntity>) = mAppDao.insertMovies(movies)
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>> = mAppDao.getBookmarkedMovie()
    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mAppDao.updateMovie(movie)
    }

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mAppDao.getTvShows()
    fun getOthersTvShows(tvShowId:Int): LiveData<List<TvShowEntity>> = mAppDao.getOthersTvShows(tvShowId)
    fun getTvShowById(movieId:Int): LiveData<List<TvShowEntity>> = mAppDao.getTvShowById(movieId)
    fun insertTvShows(tvShows: List<TvShowEntity>) = mAppDao.insertTvShows(tvShows)



}