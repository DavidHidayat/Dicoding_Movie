package com.example.dicodingmovie.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.dicodingmovie.data.AppRepository
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.vo.Resource
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(appRepository)
    }
    @Test
    fun `getTvShows should be success`() {
        val tvShows = PagedTestDataSources.snapshot(DataDummy.generateDummyTvShow())
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        Mockito.`when`(appRepository.getAllTvShows()).thenReturn(expected)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value

        val actualValue = viewModel.getTvShows().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.data, actualValue?.data)
        Assert.assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvShows should be success but data is empty`() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        Mockito.`when`(appRepository.getAllTvShows()).thenReturn(expected)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvShows().value?.data?.size
        org.junit.Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<TvShowEntity>) : PositionalDataSource<TvShowEntity>() {
        companion object {
            fun snapshot(items: List<TvShowEntity> = listOf()): PagedList<TvShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}