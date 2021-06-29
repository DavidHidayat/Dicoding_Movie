package com.example.dicodingmovie.ui.moviefavorite.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var observer: Observer<MovieFavoriteEntity>

    @Before
    fun setup() {
        viewModel = DetailMovieFavoriteViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun `get movies returns success`() {
        val movieFavorite = MovieFavoriteEntity(
            adult = false,
            backdropPath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            id = 337404,
            originalLanguage = "en",
            originalTitle = "Cruella",
            overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella\u2019s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            popularity = 5771.292,
            posterPath = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            releaseDate = "2021-05-26",
            title = "Cruella",
            video = false,
            voteAverage = 8.6,
            voteCount = 2515
        )
        val liveData = MutableLiveData<MovieFavoriteEntity>().apply {
            value = movieFavorite
        }

        Mockito.`when`(appRepository.getMovieFavoriteById(movieId)).thenReturn(liveData)
        viewModel.movieFavoriteById.observeForever(observer)
        Mockito.verify(observer).onChanged(liveData.value)

        org.junit.Assert.assertNotNull(liveData.value)
        Assert.assertEquals(liveData.value?.id, movieFavorite.id)
        Assert.assertEquals(liveData.value?.title, movieFavorite.title)

    }

    @Test
    fun  `setNextPage, repository was not executed`(){
        viewModel.setNextPage
        verify(appRepository, never()).nextMovieFavorite(any())
    }

    @Test
    fun  `setPrevPage, repository was not executed`(){
        viewModel.setPrevPage
        verify(appRepository, never()).prevMovieFavorite(any())
    }

}