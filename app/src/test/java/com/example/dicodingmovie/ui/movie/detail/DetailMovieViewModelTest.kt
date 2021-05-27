package com.example.dicodingmovie.ui.movie.detail

import com.example.dicodingmovie.ui.movie.MovieViewModel
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.poster_path, movieEntity.poster_path)
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities.size)
    }
}