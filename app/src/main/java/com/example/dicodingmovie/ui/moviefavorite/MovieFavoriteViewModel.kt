package com.example.dicodingmovie.ui.moviefavorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.vo.Resource

class MovieFavoriteViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getMovies(): LiveData<PagedList<MovieFavoriteEntity>> = appRepository.getFavoritedMovie()

    fun removeFavorite(movieFavoriteEntity: MovieFavoriteEntity) {
        if (movieFavoriteEntity != null) {
            appRepository.deleteMovieFavorite(movieFavoriteEntity.id)
        }
    }
    fun insertFavorite(movie: MovieFavoriteEntity) {
        if (movie != null) {
            val movie_data = MovieEntity(
                movie.adult,
                movie.backdropPath,
                movie.id,
                movie.originalLanguage,
                movie.originalTitle,
                movie.overview,
                movie.popularity,
                movie.posterPath,
                movie.releaseDate,
                movie.title,
                movie.video,
                movie.voteAverage,
                movie.voteCount
            )
            appRepository.insertMovieFavorite(movie_data)
        }
    }


}