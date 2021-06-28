package com.example.dicodingmovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.dicodingmovie.data.source.local.LocalDataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.utils.AppExecutors
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.utils.LiveDataTestUtil
import com.example.dicodingmovie.utils.PagedListUtil
import com.example.dicodingmovie.vo.Resource
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val appRepository = FakeAppRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieData = movieResponses[0]
    private val movieId = movieResponses[0].id
    private val movieLocal = DataDummy.generateDummyMovies()
    private val movieLocalData = movieLocal[0]
    private val movieLocalId = movieLocal[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowData = tvShowResponses[0]
    private val tvShowId = tvShowResponses[0].id
    private val tvShowLocal = DataDummy.generateDummyTvShow()
    private val tvShowLocalData = tvShowLocal[0]
    private val tvShowLocalId = tvShowLocal[0].id

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        appRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getOtherMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getOthersMovies(movieId)).thenReturn(dataSourceFactory)
        appRepository.getOthersMovies(movieId)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getOthersMovies(movieId)
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyModules = MutableLiveData<MovieEntity>()
        dummyModules.value = DataDummy.generateDummyMovies()[0]
        Mockito.`when`(local.getMovieById(movieLocalId)).thenReturn(dummyModules)

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getMovieById(movieLocalId))
        verify(local).getMovieById(eq(movieLocalId))

        assertNotNull(movieEntities)
        assertEquals(movieLocalData.id, movieEntities.data?.id)
        assertEquals(movieLocalData.title, movieEntities.data?.title)
        assertEquals(movieLocalData.releaseDate, movieEntities.data?.releaseDate)
        assertEquals(movieLocalData.overview, movieEntities.data?.overview)
        assertEquals(movieLocalData.posterPath, movieEntities.data?.posterPath)
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        appRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShows()
        Assert.assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getOtherTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getOthersTvShows(tvShowId)).thenReturn(dataSourceFactory)
        appRepository.getOthersTvShows(tvShowId)

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getOthersTvShows(tvShowId)
        Assert.assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = MutableLiveData<TvShowEntity>()
        dummyTvShows.value = DataDummy.generateDummyTvShow()[0]
        Mockito.`when`(local.getTvShowById(tvShowLocalId)).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(appRepository.getTvShowById(tvShowLocalId))
        verify(local).getTvShowById(eq(tvShowLocalId))

        assertNotNull(tvShowEntities)
        assertEquals(tvShowLocalData.id, tvShowEntities.data?.id)
        assertEquals(tvShowLocalData.name, tvShowEntities.data?.name)
        assertEquals(tvShowLocalData.firstAirDate, tvShowEntities.data?.firstAirDate)
        assertEquals(tvShowLocalData.overview, tvShowEntities.data?.overview)
        assertEquals(tvShowLocalData.posterPath, tvShowEntities.data?.posterPath)
    }

    @Test
    fun getFavoritedMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieFavoriteEntity>
        Mockito.`when`(local.getFavoritedMovie()).thenReturn(dataSourceFactory)
        appRepository.getFavoritedMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoritedMovie()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieFavoriteById() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieFavoriteEntity>
        Mockito.`when`(local.getMovieFavoriteById(movieId)).thenReturn(dataSourceFactory)
        appRepository.getMovieFavoriteById(movieId)

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getMovieById(movieLocalId))
        verify(local).getMovieFavoriteById(eq(movieLocalId))

        assertNotNull(movieEntities)
        assertEquals(movieData.id, movieEntities.data?.id)
        assertEquals(movieData.title, movieEntities.data?.title)
        assertEquals(movieData.releaseDate, movieEntities.data?.releaseDate)
        assertEquals(movieData.overview, movieEntities.data?.overview)
        assertEquals(movieData.posterPath, movieEntities.data?.posterPath)
    }

    @Test
    fun getFavoritedTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowFavoriteEntity>
        Mockito.`when`(local.getFavoritedTvShow()).thenReturn(dataSourceFactory)
        appRepository.getFavoritedTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getFavoritedTvShow()
        Assert.assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowFavoriteById() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback)
                .onTvShowReceived(tvShowData)
            null
        }.`when`(local).getTvShowFavoriteById(eq(tvShowId))

        val tvShowEntities = LiveDataTestUtil.getValue(appRepository.getTvShowById(tvShowId))
        verify(local).getTvShowFavoriteById(eq(tvShowId))

        assertNotNull(tvShowEntities)
        assertEquals(tvShowData.id, tvShowEntities.data?.id)
        assertEquals(tvShowData.name, tvShowEntities.data?.name)
        assertEquals(tvShowData.firstAirDate, tvShowEntities.data?.firstAirDate)
        assertEquals(tvShowData.overview, tvShowEntities.data?.overview)
        assertEquals(tvShowData.posterPath, tvShowEntities.data?.posterPath)
    }

}