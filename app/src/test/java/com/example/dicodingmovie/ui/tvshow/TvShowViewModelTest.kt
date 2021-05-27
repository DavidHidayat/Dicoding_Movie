package com.example.dicodingmovie.ui.tvshow

import com.example.dicodingmovie.ui.movie.MovieViewModel
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getMovies() {
        val tvShowEntities = viewModel.getTvShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities.size)
    }
}