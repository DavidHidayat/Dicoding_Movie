package com.example.dicodingmovie.ui.tvshow.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.vo.Resource

class DetailTvShowViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var tvShowById: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            appRepository.getTvShowById(mTvShowId)
        }
    var tvShowFavoriteById: LiveData<TvShowFavoriteEntity> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            appRepository.getTvShowFavoriteById(mTvShowId)
        }

    var getOthersTvShows: LiveData<Resource<PagedList<TvShowEntity>>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            appRepository.getOthersTvShows(mTvShowId)
        }

    fun setFavorite() {
        val tvShow = tvShowById.value
        val tvShowFavorite = tvShowFavoriteById.value
        if (tvShowFavorite != null) {
            appRepository.deleteTvShowFavorite(tvShowFavorite.id)
        } else {
            if (tvShow != null) {
                if (tvShow.data != null) {
                    appRepository.insertTvShowFavorite(tvShow.data)

                }
            }
        }
    }
}