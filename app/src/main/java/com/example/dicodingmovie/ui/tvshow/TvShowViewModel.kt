package com.example.dicodingmovie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.vo.Resource

class TvShowViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = appRepository.getAllTvShows()
}