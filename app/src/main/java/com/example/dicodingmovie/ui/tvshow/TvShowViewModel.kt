package com.example.dicodingmovie.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getTvShows(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}