package com.example.dicodingmovie.ui.tvshowfavorite.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.ui.moviefavorite.detail.DetailMovieFavoriteViewModel
import com.example.dicodingmovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowFavoriteViewModelTest {
    private lateinit var viewModel: DetailTvShowFavoriteViewModel
    private val dummyTvShw = DataDummy.generateDummyTvShow()[0]
    private val tvShowId = dummyTvShw.id

    @Mock
    private lateinit var appRepository: AppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TvShowFavoriteEntity>

    @Before
    fun setup() {
        viewModel = DetailTvShowFavoriteViewModel(appRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun `get tv show returns success`() {
        val tvShowFavorite = TvShowFavoriteEntity(
            backdropPath =  "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
            firstAirDate =  "2011-04-17",
            id =  1399,
            name =  "Game of Thrones",
            originalLanguage =  "en",
            originalName =  "Game of Thrones",
            overview =  "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            popularity =  446.618,
            posterPath =  "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            voteAverage =  8.4,
            voteCount =  14696
        )
        val liveData = MutableLiveData<TvShowFavoriteEntity>().apply {
            value = tvShowFavorite
        }

        Mockito.`when`(appRepository.getTvShowFavoriteById(tvShowId)).thenReturn(liveData)
        viewModel.tvShowFavoriteById.observeForever(observer)
        Mockito.verify(observer).onChanged(liveData.value)

        org.junit.Assert.assertNotNull(liveData.value)
        Assert.assertEquals(liveData.value?.id, tvShowFavorite.id)
        Assert.assertEquals(liveData.value?.name, tvShowFavorite.name)

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