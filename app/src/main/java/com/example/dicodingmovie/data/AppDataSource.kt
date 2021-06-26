package com.example.dicodingmovie.data

import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.vo.Resource

interface AppDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getOthersMovies(movieId: Int?): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>>
    fun getOthersTvShows(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>>

    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>
    fun setMovieBookmark(movie: MovieEntity, state: Boolean)

}