package com.example.dicodingmovie.ui.tvshow.detail

import androidx.lifecycle.ViewModel
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.utils.DataDummy

class DetailTvShowViewModel: ViewModel() {
    private var tvShowId: Int = 0

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tv_show: TvShowEntity
        val tvShowsEntities = DataDummy.generateDummyTvShow()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.id == tvShowId) {
                tv_show = tvShowEntity
            }
        }
        return tv_show
    }

    fun getTvShows(): List<TvShowEntity> {
        val tv_shows = ArrayList<TvShowEntity>()
        val tvShowEntities = DataDummy.generateDummyTvShow()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id != tvShowId) {
                tv_shows.add(tvShowEntity)
            }
        }
        return tv_shows
    }
}