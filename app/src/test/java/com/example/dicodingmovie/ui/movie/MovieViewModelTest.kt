package com.example.dicodingmovie.ui.movie

import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest  {
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(appRepository)
    }

    @Test
    fun getMovies() {
        `when`(appRepository.getAllMovies()).thenReturn( DataDummy.generateDummyMovies()))
        val movieEntities = viewModel.getMovies()
        verify<AppRepository>(appRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities.size)
    }
}