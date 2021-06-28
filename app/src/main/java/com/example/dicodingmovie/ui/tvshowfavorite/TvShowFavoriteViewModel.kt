package com.example.dicodingtvShow.ui.tvshowfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity

class TvShowFavoriteViewModel (private val appRepository: AppRepository): ViewModel() {
    fun getTvShows(): LiveData<PagedList<TvShowFavoriteEntity>> = appRepository.getFavoritedTvShow()

    fun removeFavorite(tvShowFavoriteEntity: TvShowFavoriteEntity) {
        if (tvShowFavoriteEntity != null) {
            appRepository.deleteTvShowFavorite(tvShowFavoriteEntity.id)
        }
    }
    fun insertFavorite(tvShow: TvShowFavoriteEntity) {
        if (tvShow != null) {
            val tvShow_data = TvShowEntity(
                tvShow.backdropPath,
                tvShow.firstAirDate,
                tvShow.id,
                tvShow.name,
                tvShow.originalLanguage,
                tvShow.originalName,
                tvShow.overview,
                tvShow.popularity,
                tvShow.posterPath,
                tvShow.voteAverage,
                tvShow.voteCount
            )
            appRepository.insertTvShowFavorite(tvShow_data)
        }
    }
}