package com.example.dicodingmovie.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
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
class DetailMovieFavoriteViewModelTest {
    private lateinit var viewModel: DetailMovieFavoriteViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Mock
    private lateinit var appRepository: AppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var otherMoviebserver: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieFavoriteViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(appRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie()
        Mockito.verify<AppRepository>(appRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.value?.id)
        assertEquals(dummyMovie.title, movieEntity.value?.title)
        assertEquals(dummyMovie.release_date, movieEntity.value?.release_date)
        assertEquals(dummyMovie.overview, movieEntity.value?.overview)
        assertEquals(dummyMovie.poster_path, movieEntity.value?.poster_path)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getOthersMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(appRepository.getOthersMovies(movieId)).thenReturn(movies)
        val movieEntities = viewModel.getOthersMovies()
        Mockito.verify<AppRepository>(appRepository).getOthersMovies(movieId)
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.value?.size)

        viewModel.getOthersMovies().observeForever(otherMoviebserver)
        verify(otherMoviebserver).onChanged(dummyMovies)
    }
}