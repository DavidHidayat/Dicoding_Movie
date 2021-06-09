package com.example.dicodingmovie.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val moviesResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in moviesResponses) {
                    val movie = MovieEntity(
                        response.adult,
                        response.backdrop_path,
                        response.genre_ids,
                        response.id,
                        response.original_language,
                        response.original_title,
                        response.overview,
                        response.popularity,
                        response.poster_path,
                        response.release_date,
                        response.title,
                        response.video,
                        response.vote_average,
                        response.vote_count,
                    )
                    movieList.add(movie)
                }
                moviesResults.postValue(movieList)
            }
        })
        return moviesResults
    }

    override fun getMovie(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovie(movieId, object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieResponses: MovieResponse) {
                lateinit var movie: MovieEntity
                movie = MovieEntity(
                    movieResponses.adult,
                    movieResponses.backdrop_path,
                    movieResponses.genre_ids,
                    movieResponses.id,
                    movieResponses.original_language,
                    movieResponses.original_title,
                    movieResponses.overview,
                    movieResponses.popularity,
                    movieResponses.poster_path,
                    movieResponses.release_date,
                    movieResponses.title,
                    movieResponses.video,
                    movieResponses.vote_average,
                    movieResponses.vote_count,
                )
                movieResult.postValue(movie)
            }

        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowsResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShow(object :RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowsResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowsResponses) {
                    val tvShow = TvShowEntity(
                        response.backdrop_path,
                        response.first_air_date,
                        response.id,
                        response.name,
                        response.original_language,
                        response.original_name,
                        response.overview,
                        response.popularity,
                        response.poster_path,
                        response.vote_average,
                        response.vote_count,
                    )
                    tvShowList.add(tvShow)
                }
                tvShowsResults.postValue(tvShowList)
            }
        })
        return tvShowsResults

    }

    override fun getTvShow(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowsResponses: List<TvShowResponse>) {
                lateinit var tv_show: TvShowEntity
                for (response in tvShowsResponses) {
                    if (response.id == tvShowId) {
                        tv_show = TvShowEntity(
                            response.backdrop_path,
                            response.first_air_date,
                            response.id,
                            response.name,
                            response.original_language,
                            response.original_name,
                            response.overview,
                            response.popularity,
                            response.poster_path,
                            response.vote_average,
                            response.vote_count,
                        )
                        tvShowResult.postValue(tv_show)
                    }
                }
            }
        })
        return tvShowResult
    }
}