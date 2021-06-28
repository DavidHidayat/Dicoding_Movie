package com.example.dicodingmovie.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.vo.Resource

interface AppDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getOthersMovies(movieId: Int): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoritedMovie(): LiveData<PagedList<MovieFavoriteEntity>>
    fun getMovieFavoriteById(movieId: Int): LiveData<MovieFavoriteEntity>
    fun insertMovieFavorite(movie: MovieEntity)
    fun deleteMovieFavorite(movieId: Int)

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getOthersTvShows(tvShowId: Int): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoritedTvShow(): LiveData<PagedList<TvShowFavoriteEntity>>
    fun getTvShowFavoriteById(tvShowId: Int): LiveData<TvShowFavoriteEntity>
    fun insertTvShowFavorite(tvShow: TvShowEntity)
    fun deleteTvShowFavorite(tvShowId: Int)

}