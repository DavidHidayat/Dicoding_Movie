package com.example.dicodingmovie.ui.moviefavorite.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.vo.Resource

class DetailMovieFavoriteViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movieById: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            appRepository.getMovieById(mMovieId)
        }
    var movieFavoriteById: LiveData<MovieFavoriteEntity> =
        Transformations.switchMap(movieId) { mMovieId ->
            appRepository.getMovieFavoriteById(mMovieId)
        }

    fun setFavorite() {
        val movie = movieById.value
        val movieFavorite = movieFavoriteById.value
        if (movieFavorite != null) {
            appRepository.deleteMovieFavorite(movieFavorite.id)
        } else {
            if (movie != null) {
                if (movie.data != null) {
                    appRepository.insertMovieFavorite(movie.data)
                }
            }
        }
    }

    var setNextPage: LiveData<MovieFavoriteEntity> =
        Transformations.switchMap(movieId) { mMovieId ->
            appRepository.nextMovieFavorite(mMovieId)
        }

    var setPrevPage: LiveData<MovieFavoriteEntity> =
        Transformations.switchMap(movieId) { mMovieId ->
            appRepository.prevMovieFavorite(mMovieId)
        }

}