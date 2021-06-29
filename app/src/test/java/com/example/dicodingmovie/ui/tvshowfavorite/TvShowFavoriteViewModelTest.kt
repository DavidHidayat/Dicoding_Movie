package com.example.dicodingmovie.ui.tvshowfavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingtvShow.ui.tvshowfavorite.TvShowFavoriteViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
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
class TvShowFavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowFavoriteEntity>>

    @Mock
    private lateinit var appRepository: AppRepository

    private lateinit var viewModel: TvShowFavoriteViewModel

    private val tvShowFavorite = TvShowFavoriteEntity(
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

    @Before
    fun setup() {
        viewModel = TvShowFavoriteViewModel(appRepository)
    }

    @Test
    fun `get movies returns success`() {
        val pagedList = mock<PagedList<TvShowFavoriteEntity>>()
        Mockito.`when`(pagedList.size).thenReturn(1)
        Mockito.`when`(pagedList[0]).thenReturn(tvShowFavorite)
        val liveData = MutableLiveData<PagedList<TvShowFavoriteEntity>>().apply {
            value = pagedList
        }

        Mockito.`when`(appRepository.getFavoritedTvShow()).thenReturn(liveData)
        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(liveData.value)

        Assert.assertNotNull(liveData.value)
        Assert.assertEquals(liveData.value?.get(0), pagedList[0])
        Assert.assertEquals(liveData.value?.size, pagedList.size)

    }

    @Test
    fun  `removeFavorite, repository was not executed`(){
        viewModel.removeFavorite(null)
        verify(appRepository, never()).deleteTvShowFavorite(any())
    }

    @Test
    fun `insertFavorite, repository was executed`() {
        viewModel.insertFavorite(tvShowFavorite)
        verify(appRepository).insertTvShowFavorite(any())
    }


}