package com.example.dicodingmovie.data.source

import android.util.Log
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.RemoteDataSource

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) : AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()

        val movieList = ArrayList<MovieEntity>()
        for (response in movieResponses) {
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
        return movieList
    }

    override fun getMovie(movieId: Int): MovieEntity {
        val movieResponses = remoteDataSource.getAllMovies()
        lateinit var movie: MovieEntity
        for (response in movieResponses) {
            if (response.id == movieId) {
                movie = MovieEntity(
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
            }
        }
        return movie
    }

    override fun getAllTvShow(): List<TvShowEntity> {
        val tvShowResponses = remoteDataSource.getAllTvShow()
        val tvShowList = ArrayList<TvShowEntity>()
        for (response in tvShowResponses) {
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
        return tvShowList
    }

    override fun getTvShow(tvShowId: Int): TvShowEntity {
        val tvShowResponses = remoteDataSource.getAllTvShow()
        lateinit var tv_show: TvShowEntity
        for (response in tvShowResponses) {
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
            }
        }
        return tv_show
    }
}