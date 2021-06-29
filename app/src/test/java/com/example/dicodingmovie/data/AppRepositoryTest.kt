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
        val liveDataMovieFavorite = MutableLiveData<MovieFavoriteEntity>().apply {
            value = MovieFavoriteEntity(
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
        }

        Mockito.`when`(local.getMovieFavoriteById(movieId)).thenReturn(liveDataMovieFavorite)

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getMovieFavoriteById(movieId))
        verify(local).getMovieFavoriteById(eq(movieId))

        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(movieData.id, movieEntities.id)
        Assert.assertEquals(movieData.title, movieEntities.title)
        Assert.assertEquals(movieData.releaseDate, movieEntities.releaseDate)
        Assert.assertEquals(movieData.overview, movieEntities.overview)
        Assert.assertEquals(movieData.posterPath, movieEntities.posterPath)
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
        doAnswer {
            return@doAnswer MutableLiveData<TvShowFavoriteEntity>().apply {
                value = TvShowFavoriteEntity(
                    "/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg",
                    "2021-03-24",
                    120168,
                    "Who Killed Sara?",
                    "es",
                    "¿Quién mató a Sara?",
                    "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                    1606.074,
                    "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                    7.8,
                    658
                )
            }
        }.`when`(local).getTvShowFavoriteById(eq(tvShowId))

        val tvShowEntities = LiveDataTestUtil.getValue(appRepository.getTvShowFavoriteById(tvShowId))
        verify(local).getTvShowFavoriteById(eq(tvShowId))

        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(tvShowData.id, tvShowEntities.id)
        Assert.assertEquals(tvShowData.name, tvShowEntities.name)
        Assert.assertEquals(tvShowData.firstAirDate, tvShowEntities.firstAirDate)
        Assert.assertEquals(tvShowData.overview, tvShowEntities.overview)
        Assert.assertEquals(tvShowData.posterPath, tvShowEntities.posterPath)
    }

}