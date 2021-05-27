package com.example.dicodingmovie.ui.tvshow.detail

import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest{
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedTvShow(dummyTvShow.id)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.name, tvShowEntity.name)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity.first_air_date)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.poster_path, tvShowEntity.poster_path)
    }

    @Test
    fun getMovies() {
        val tvShowEntities = viewModel.getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(9, tvShowEntities.size)
    }    
}