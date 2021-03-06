package com.example.dicodingmovie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy

class TvShowViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = appRepository.getAllTvShow()
}