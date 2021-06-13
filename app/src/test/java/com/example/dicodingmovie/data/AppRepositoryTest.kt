package com.example.dicodingmovie.data

import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
import com.example.dicodingmovie.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AppRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val appRepository = FakeAppRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieData = movieResponses[0]
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateRemoteDummyTvShow()
    private val tvShowData = tvShowResponses[0]
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getAllMovies() {
        `when`<List<MovieResponse>>(remote.getAllMovies()).thenReturn(movieResponses)
        val movieEntities = appRepository.getAllMovies()
        verify<RemoteDataSource>(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getOtherMovies() {
        `when`<List<MovieResponse>>(remote.getOtherMovies(movieId)).thenReturn(movieResponses)
        val movieEntities = appRepository.getOthersMovies(movieId)
        verify<RemoteDataSource>(remote).getOtherMovies(movieId)
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        `when`<MovieResponse>(remote.getMovie(movieId)).thenReturn(movieData)
        val movieEntities = appRepository.getMovie(movieId)
        verify<RemoteDataSource>(remote).getMovie(movieId)
        assertNotNull(movieEntities)
        assertEquals(movieData.id, movieEntities.id)
        assertEquals(movieData.title, movieEntities.title)
        assertEquals(movieData.release_date, movieEntities.release_date)
        assertEquals(movieData.overview, movieEntities.overview)
        assertEquals(movieData.poster_path, movieEntities.poster_path)
    }

    @Test
    fun getAllTvShow() {
        `when`<List<TvShowResponse>>(remote.getAllTvShow()).thenReturn(tvShowResponses)
        val tvShowResponse = appRepository.getAllTvShows()
        verify<RemoteDataSource>(remote).getAllTvShow()
        assertNotNull(tvShowResponse)
        assertEquals(tvShowResponses.size.toLong(), tvShowResponse.size.toLong())
    }

    @Test
    fun getOtherTvShows() {
        `when`<List<TvShowResponse>>(remote.getOtherTvShows(tvShowId)).thenReturn(tvShowResponses)
        val tvShowResponse = appRepository.getOthersTvShows(tvShowId)
        verify<RemoteDataSource>(remote).getOtherTvShows(tvShowId)
        assertNotNull(tvShowResponse)
        assertEquals(tvShowResponses.size.toLong(), tvShowResponse.size.toLong())
    }

    @Test
    fun getTvShow() {
        `when`<TvShowResponse>(remote.getTvShow(tvShowId)).thenReturn(tvShowData)
        val tvShowEntities = appRepository.getTvShow(tvShowId)
        verify<RemoteDataSource>(remote).getTvShow(tvShowId)
        assertNotNull(tvShowEntities)
        assertEquals(tvShowData.id, tvShowEntities.id)
        assertEquals(tvShowData.name, tvShowEntities.name)
        assertEquals(tvShowData.first_air_date, tvShowEntities.first_air_date)
        assertEquals(tvShowData.overview, tvShowEntities.overview)
        assertEquals(tvShowData.poster_path, tvShowEntities.poster_path)
    }
    
}