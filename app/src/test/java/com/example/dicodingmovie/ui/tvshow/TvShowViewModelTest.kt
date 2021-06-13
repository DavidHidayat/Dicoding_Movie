package com.example.dicodingmovie.ui.tvshow

import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(appRepository)
    }

    @Test
    fun getMovies() {
        Mockito.`when`(appRepository.getAllTvShows()).thenReturn(DataDummy.generateDummyTvShow())
        val tvShowEntities = viewModel.getTvShows()
        Mockito.verify<AppRepository>(appRepository).getAllTvShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities.size)
    }
}