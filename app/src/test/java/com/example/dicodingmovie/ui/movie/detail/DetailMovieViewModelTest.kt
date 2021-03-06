package com.example.dicodingmovie.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModel
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    @Mock
    private lateinit var appRepository: AppRepository
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>().apply {
            value = dummyMovie
        }
        `when`(appRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie()
        verify<AppRepository>(appRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.value?.id)
        assertEquals(dummyMovie.title, movieEntity.value?.title)
        assertEquals(dummyMovie.release_date, movieEntity.value?.release_date)
        assertEquals(dummyMovie.overview, movieEntity.value?.overview)
        assertEquals(dummyMovie.poster_path, movieEntity.value?.poster_path)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<MovieEntity>>().apply {
            value = DataDummy.generateDummyMovies()
        }
        `when`(appRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies()
        verify<AppRepository>(appRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.value?.size)
    }
}