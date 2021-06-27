package com.example.dicodingmovie.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.di.Injection
import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.ui.movie.detail.DetailMovieViewModel
import com.example.dicodingmovie.ui.moviefavorite.MovieFavoriteViewModel
import com.example.dicodingmovie.ui.moviefavorite.detail.DetailMovieFavoriteViewModel
import com.example.dicodingmovie.ui.tvshow.TvShowViewModel
import com.example.dicodingmovie.ui.tvshow.detail.DetailTvShowViewModel

class ViewModelFactory private constructor(private val mAppRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieFavoriteViewModel::class.java) -> {
                DetailMovieFavoriteViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(mAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}