package com.example.dicodingmovie.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.source.AppRepository
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(appRepository)
    }

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<MovieEntity>>().apply {
            value = DataDummy.generateDummyMovies()
        }
        `when`(appRepository.getAllMovies()).thenReturn( movies)
        val movieEntities = viewModel.getMovies()
        verify<AppRepository>(appRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.value?.size)
    }
}