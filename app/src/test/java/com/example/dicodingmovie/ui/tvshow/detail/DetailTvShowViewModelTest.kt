package com.example.dicodingmovie.ui.tvshow.detail

import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest{
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShow.id

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        Mockito.`when`(appRepository.getTvShow(tvShowId)).thenReturn(dummyTvShow)
        val tvShowEntity = viewModel.getTvShow()
        Mockito.verify<AppRepository>(appRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.name, tvShowEntity.name)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity.first_air_date)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.poster_path, tvShowEntity.poster_path)
    }

    @Test
    fun getOthersTvShows() {
        Mockito.`when`(appRepository.getOthersTvShows(tvShowId)).thenReturn(DataDummy.generateDummyTvShow())
        val tvShowEntities = viewModel.getOthersTvShows()
        Mockito.verify<AppRepository>(appRepository).getOthersTvShows(tvShowId)
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)

    }    
}