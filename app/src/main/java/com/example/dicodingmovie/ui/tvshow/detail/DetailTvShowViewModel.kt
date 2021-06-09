package com.example.dicodingmovie.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy

class DetailTvShowViewModel(private val appRepository: AppRepository): ViewModel() {
    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShowEntity> = appRepository.getTvShow(tvShowId)

    fun getTvShows(): LiveData<List<TvShowEntity>> = appRepository.getAllTvShow()
}