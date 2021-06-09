package com.example.dicodingmovie.ui.movie.detail

import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    @Mock
    private lateinit var appRepository: AppRepository
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        `when`(appRepository.getMovie(movieId)).thenReturn(dummyMovie)
        val movieEntity = viewModel.getMovie()
        verify<AppRepository>(appRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.poster_path, movieEntity.poster_path)
    }

    @Test
    fun getMovies() {
        `when`(appRepository.getAllMovies()).thenReturn(ArrayList( DataDummy.generateDummyMovies()))
        val movieEntities = viewModel.getMovies()
        verify<AppRepository>(appRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities.size)
    }
}