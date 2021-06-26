package com.example.dicodingmovie.ui.tvshow

import com.example.dicodingmovie.data.source.local.entity.TvShowEntity

interface TvShowFragmentCallback {
    fun onShareClick(tv_show: TvShowEntity)
}
