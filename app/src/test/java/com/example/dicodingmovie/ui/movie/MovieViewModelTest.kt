package com.example.dicodingmovie.ui.movie

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MovieViewModelTest  {
    private lateinit var viewModel: MovieViewModel
    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.size)
    }
}