package com.example.dicodingmovie.ui.tvshow

import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
        `when`(appRepository.getAllTvShow()).thenReturn(ArrayList( DataDummy.generateDummyTvShow()))
        val tvShowEntities = viewModel.getTvShows()
        verify<AppRepository>(appRepository).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}