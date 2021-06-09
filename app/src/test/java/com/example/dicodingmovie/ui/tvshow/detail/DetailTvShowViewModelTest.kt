package com.example.dicodingmovie.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest{
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]

    @Mock
    private lateinit var appRepository: AppRepository
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>().apply {
            value = dummyTvShow
        }
        `when`(appRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow()
        verify<AppRepository>(appRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.value?.id)
        assertEquals(dummyTvShow.name, tvShowEntity.value?.name)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity.value?.first_air_date)
        assertEquals(dummyTvShow.overview, tvShowEntity.value?.overview)
        assertEquals(dummyTvShow.poster_path, tvShowEntity.value?.poster_path)
    }

    @Test
    fun getTvShows() {
        val tvShows = MutableLiveData<List<TvShowEntity>>().apply {
            value = DataDummy.generateDummyTvShow()
        }
        `when`(appRepository.getAllTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows()
        verify<AppRepository>(appRepository).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.value?.size)
    }
}