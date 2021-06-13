package com.example.dicodingmovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.utils.LiveDataTestUtil
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class AppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val appRepository = FakeAppRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieData = movieResponses[0]
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowData = tvShowResponses[0]
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getAllMovies())
        verify(remote).getAllMovies(any())

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getOtherMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadOthersMoviesCallback)
                .onOthersMoviesReceived(movieResponses)
            null
        }.`when`(remote).getOtherMovies(eq(movieId),any())

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getOthersMovies(movieId))
        verify(remote).getOtherMovies(eq(movieId), any())

        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                .onMovieReceived(movieData)
            null
        }.`when`(remote).getMovie(eq(movieId),any())

        val movieEntities = LiveDataTestUtil.getValue(appRepository.getMovie(movieId))
        verify(remote).getMovie(eq(movieId), any())

        assertNotNull(movieEntities)
        assertEquals(movieData.id, movieEntities.id)
        assertEquals(movieData.title, movieEntities.title)
        assertEquals(movieData.release_date, movieEntities.release_date)
        assertEquals(movieData.overview, movieEntities.overview)
        assertEquals(movieData.poster_path, movieEntities.poster_path)
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShow(any())

        val tvShowResponse = LiveDataTestUtil.getValue(appRepository.getAllTvShows())
        verify(remote).getAllTvShow(any())

        assertNotNull(tvShowResponse)
        assertEquals(tvShowResponses.size.toLong(), tvShowResponse.size.toLong())
    }

    @Test
    fun getOtherTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadOthersTvShowsCallback)
                .onOthersTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getOtherTvShows(eq(tvShowId),any())

        val tvShowResponse = LiveDataTestUtil.getValue(appRepository.getOthersTvShows(tvShowId))
        verify(remote).getOtherTvShows(eq(tvShowId), any())

        assertNotNull(tvShowResponse)
        assertEquals(tvShowResponses.size.toLong(), tvShowResponse.size.toLong())
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback)
                .onTvShowReceived(tvShowData)
            null
        }.`when`(remote).getTvShow(eq(tvShowId),any())

        val tvShowEntities = LiveDataTestUtil.getValue(appRepository.getTvShow(tvShowId))
        verify(remote).getTvShow(eq(tvShowId), any())

        assertNotNull(tvShowEntities)
        assertEquals(tvShowData.id, tvShowEntities.id)
        assertEquals(tvShowData.name, tvShowEntities.name)
        assertEquals(tvShowData.first_air_date, tvShowEntities.first_air_date)
        assertEquals(tvShowData.overview, tvShowEntities.overview)
        assertEquals(tvShowData.poster_path, tvShowEntities.poster_path)
    }
    
}