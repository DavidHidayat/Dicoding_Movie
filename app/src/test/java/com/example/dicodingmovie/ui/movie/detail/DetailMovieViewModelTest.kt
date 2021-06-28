package com.example.dicodingmovie.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.ui.movie.MovieViewModelTest
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.vo.Resource
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
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Mock
    private lateinit var appRepository: AppRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var otherMovieObserver: Observer<Resource<PagedList<MovieEntity>>>


    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun movieById() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(dummyMovie)

        Mockito.`when`(appRepository.getMovieById(movieId)).thenReturn(expected)

        viewModel.movieById.observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.movieById.value

        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        assertEquals(expectedValue, actualValue)

    }

    @Test
    fun getOthersMovies() {
        val movies = MovieViewModelTest.PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)


        Mockito.`when`(appRepository.getOthersMovies(movieId)).thenReturn(expected)

        viewModel.getOthersMovies.observeForever(otherMovieObserver)
        verify(otherMovieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getOthersMovies.value

        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)

    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

}