package com.example.dicodingmovie.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.ui.tvshow.TvShowViewModelTest
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.vo.Resource
import junit.framework.Assert.assertEquals
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
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var otherTvShowbserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun tvShowById() {
        val expected = MutableLiveData<Resource<TvShowEntity>>()
        expected.value = Resource.success(dummyTvShow)

        Mockito.`when`(appRepository.getTvShowById(tvShowId)).thenReturn(expected)

        viewModel.tvShowById.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.tvShowById.value

        val movie = MutableLiveData<TvShowEntity>()
        movie.value = dummyTvShow

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getOthersTvShows() {
        val dummyTvShows = TvShowViewModelTest.PagedTestDataSources.snapshot(DataDummy.generateDummyTvShow())
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(dummyTvShows)

        Mockito.`when`(appRepository.getOthersTvShows(tvShowId)).thenReturn(expected)

        viewModel.getOthersTvShows.observeForever(otherTvShowbserver)
        verify(otherTvShowbserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getOthersTvShows.value

        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }
}