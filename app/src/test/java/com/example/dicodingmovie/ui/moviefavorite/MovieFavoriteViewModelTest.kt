package com.example.dicodingmovie.ui.moviefavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<MovieFavoriteEntity>>

    @Mock
    private lateinit var appRepository: AppRepository

    private lateinit var viewModel: MovieFavoriteViewModel

    @Before
    fun setup() {
        viewModel = MovieFavoriteViewModel(appRepository)
    }

    private val movieFavorite = MovieFavoriteEntity(
        false,
        "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
        460465,
        "en",
        "Mortal Kombat",
        "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
        2437.17,
        "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
        "2021-04-07",
        "Mortal Kombat",
        false,
        7.6,
        2594
    )
    @Test
    fun `get movies returns success`() {
        val pagedList = mock<PagedList<MovieFavoriteEntity>>()
        Mockito.`when`(pagedList.size).thenReturn(1)
        Mockito.`when`(pagedList[0]).thenReturn(movieFavorite)
        val liveDataMovies = MutableLiveData<PagedList<MovieFavoriteEntity>>().apply {
            value = pagedList
        }

        Mockito.`when`(appRepository.getFavoritedMovie()).thenReturn(liveDataMovies)
        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(liveDataMovies.value)

        org.junit.Assert.assertNotNull(liveDataMovies.value)
        org.junit.Assert.assertEquals(liveDataMovies.value?.get(0), pagedList[0])
        org.junit.Assert.assertEquals(liveDataMovies.value?.size, pagedList.size)

    }

    @Test
    fun  `removeFavorite, repository was not executed`(){
        viewModel.removeFavorite(null)
        verify(appRepository, never()).deleteMovieFavorite(any())
    }

    @Test
    fun `insertFavorite, repository was executed`() {
        viewModel.insertFavorite(movieFavorite)
        verify(appRepository).insertMovieFavorite(any())
    }

}