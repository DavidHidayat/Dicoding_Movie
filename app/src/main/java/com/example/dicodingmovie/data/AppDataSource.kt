package com.example.dicodingmovie.data

import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.vo.Resource

interface AppDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getOthersMovies(movieId: Int?): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>>
    fun getOthersTvShows(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>>

    fun getFavoritedMovie(): LiveData<List<MovieFavoriteEntity>>
    fun getMovieFavoriteById(movieId: Int): LiveData<MovieFavoriteEntity>
    fun insertMovieFavorite(movie: MovieEntity)
    fun deleteMovieFavorite(movieId: Int)
}