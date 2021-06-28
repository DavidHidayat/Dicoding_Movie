package com.example.dicodingmovie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.data.source.local.room.AppDao

class LocalDataSource private constructor(private val mAppDao: AppDao)  {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mAppDao.getMovies()
    fun getOthersMovies(movieId: Int): DataSource.Factory<Int, MovieEntity> = mAppDao.getOthersMovies(movieId)
    fun getMovieById(movieId:Int): LiveData<MovieEntity> = mAppDao.getMovieById(movieId)
    fun insertMovies(movies: List<MovieEntity>) = mAppDao.insertMovies(movies)

    fun getFavoritedMovie(): DataSource.Factory<Int, MovieFavoriteEntity> = mAppDao.getFavoritedMovie()
    fun getMovieFavoriteById(movieId:Int): LiveData<MovieFavoriteEntity> = mAppDao.getMovieFavoriteById(movieId)
    fun insertMovieFavorite(movies: List<MovieFavoriteEntity>) = mAppDao.insertMoviesFavorite(movies)
    fun deleteMovieFavorite(movieId:Int) = mAppDao.deleteMoviesFavorite(movieId)
    fun prevMovieFavorite(movieId: Int) = mAppDao.prevMovieFavorite(movieId)
    fun nextMovieFavorite(movieId: Int) = mAppDao.nextMovieFavorite(movieId)

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mAppDao.getTvShows()
    fun getOthersTvShows(tvShowId:Int): DataSource.Factory<Int, TvShowEntity> = mAppDao.getOthersTvShows(tvShowId)
    fun getTvShowById(movieId:Int): LiveData<TvShowEntity> = mAppDao.getTvShowById(movieId)
    fun insertTvShows(tvShows: List<TvShowEntity>) = mAppDao.insertTvShows(tvShows)

    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowFavoriteEntity> = mAppDao.getFavoritedTvShow()
    fun getTvShowFavoriteById(movieId:Int): LiveData<TvShowFavoriteEntity> = mAppDao.getTvShowFavoriteById(movieId)
    fun insertTvShowFavorite(movies: List<TvShowFavoriteEntity>) = mAppDao.insertTvShowsFavorite(movies)
    fun deleteTvShowFavorite(movieId:Int) = mAppDao.deleteTvShowsFavorite(movieId)
    fun prevTvShowFavorite(movieId: Int) = mAppDao.prevTvShowFavorite(movieId)
    fun nextTvShowFavorite(movieId: Int) = mAppDao.nextTvShowFavorite(movieId)

}