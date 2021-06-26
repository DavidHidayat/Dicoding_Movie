package com.example.dicodingmovie.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.vo.Resource

class DetailTvShowViewModel(private val appRepository: AppRepository): ViewModel() {
    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>> = appRepository.getTvShow(tvShowId)

    fun getOthersTvShows(): LiveData<Resource<List<TvShowEntity>>> = appRepository.getOthersTvShows(tvShowId)
}