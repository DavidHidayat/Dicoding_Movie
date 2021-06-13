package com.example.dicodingmovie.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(appRepository)
    }

    @Test
    fun getMovies() {
        val dummyTvShow = DataDummy.generateDummyTvShow()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(appRepository.getAllTvShows()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShows()
        Mockito.verify<AppRepository>(appRepository).getAllTvShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities.value?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}