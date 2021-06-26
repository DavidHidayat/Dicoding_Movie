package com.example.dicodingmovie.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest{
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShow.id

    @Mock
    private lateinit var appRepository: AppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Mock
    private lateinit var otherTvShowbserver: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(appRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow()
        Mockito.verify<AppRepository>(appRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.value?.id)
        assertEquals(dummyTvShow.name, tvShowEntity.value?.name)
        assertEquals(dummyTvShow.first_air_date, tvShowEntity.value?.first_air_date)
        assertEquals(dummyTvShow.overview, tvShowEntity.value?.overview)
        assertEquals(dummyTvShow.poster_path, tvShowEntity.value?.poster_path)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getOthersTvShows() {
        val dummyTvShow = DataDummy.generateDummyTvShow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(appRepository.getOthersTvShows(tvShowId)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getOthersTvShows()
        Mockito.verify<AppRepository>(appRepository).getOthersTvShows(tvShowId)
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.value?.size)

        viewModel.getOthersTvShows().observeForever(otherTvShowbserver)
        Mockito.verify(otherTvShowbserver).onChanged(dummyTvShow)
    }
}