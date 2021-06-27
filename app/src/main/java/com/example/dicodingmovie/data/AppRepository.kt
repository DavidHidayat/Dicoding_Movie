package com.example.dicodingmovie.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dicodingmovie.data.source.local.LocalDataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.remote.ApiResponse
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
import com.example.dicodingmovie.utils.AppExecutors
import com.example.dicodingmovie.vo.Resource

class AppRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(responses: List<MovieResponse>) {
                val dataList = ArrayList<MovieEntity>()
                for (response in responses) {
                    val movie = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.id,
                        response.originalLanguage,
                        response.originalTitle,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.releaseDate,
                        response.title,
                        response.video,
                        response.voteAverage,
                        response.voteCount
                    )
                    Log.e("Coba", movie.toString())

                    dataList.add(movie)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieById(movieId)

            public override fun saveCallResult(response: MovieResponse) {
                val dataList = ArrayList<MovieEntity>()
                if (response != null) {
                    val movie = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.id,
                        response.originalLanguage,
                        response.originalTitle,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.releaseDate,
                        response.title,
                        response.video,
                        response.voteAverage,
                        response.voteCount
                    )
                    dataList.add(movie)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }


    override fun getOthersMovies(movieId: Int): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getOthersMovies(movieId)

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getOtherMovies(movieId)

            public override fun saveCallResult(responses: List<MovieResponse>) {
                val dataList = ArrayList<MovieEntity>()
                for (response in responses) {
                    val movie = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.id,
                        response.originalLanguage,
                        response.originalTitle,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.releaseDate,
                        response.title,
                        response.video,
                        response.voteAverage,
                        response.voteCount
                    )
                    dataList.add(movie)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(responses: List<TvShowResponse>) {
                val dataList = ArrayList<TvShowEntity>()
                for (response in responses) {
                    val course = TvShowEntity(
                        response.backdropPath,
                        response.firstAirDate,
                        response.id,
                        response.name,
                        response.originalLanguage,
                        response.originalName,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.voteAverage,
                        response.voteCount
                    )
                    dataList.add(course)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getTvShowById(tvShowId.toInt())

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow(tvShowId.toInt())

            public override fun saveCallResult(responses: List<TvShowResponse>) {
                val dataList = ArrayList<TvShowEntity>()
                for (response in responses) {
                    val course = TvShowEntity(
                        response.backdropPath,
                        response.firstAirDate,
                        response.id,
                        response.name,
                        response.originalLanguage,
                        response.originalName,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.voteAverage,
                        response.voteCount
                    )
                    dataList.add(course)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getOthersTvShows(tvShowId: Int): LiveData<Resource<List<TvShowEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowEntity>> =
                localDataSource.getOthersTvShows(tvShowId)

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getOtherTvShows(tvShowId)

            public override fun saveCallResult(responses: List<TvShowResponse>) {
                val dataList = ArrayList<TvShowEntity>()
                for (response in responses) {
                    val course = TvShowEntity(
                        response.backdropPath,
                        response.firstAirDate,
                        response.id,
                        response.name,
                        response.originalLanguage,
                        response.originalName,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.voteAverage,
                        response.voteCount
                    )
                    dataList.add(course)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovie(): LiveData<List<MovieFavoriteEntity>> =
        localDataSource.getFavoritedMovie()

    override fun getMovieFavoriteById(movieId: Int): LiveData<MovieFavoriteEntity> =
        localDataSource.getMovieFavoriteById(movieId)

    override fun insertMovieFavorite(movie: MovieEntity) = appExecutors.diskIO().execute {
        val dataList = ArrayList<MovieFavoriteEntity>()
        val movie_data = MovieFavoriteEntity(
            movie.adult,
            movie.backdropPath,
            movie.id,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.releaseDate,
            movie.title,
            movie.video,
            movie.voteAverage,
            movie.voteCount
        )
        dataList.add(movie_data)
        localDataSource.insertMovieFavorite(dataList)

    }
    override fun deleteMovieFavorite(movieId: Int) = appExecutors.diskIO().execute {
        localDataSource.deleteMovieFavorite(
            movieId
        )
    }

    fun nextMovieFavorite(movieId: Int): LiveData<MovieFavoriteEntity> = localDataSource.nextMovieFavorite(movieId)
    fun prevMovieFavorite(movieId: Int): LiveData<MovieFavoriteEntity> = localDataSource.prevMovieFavorite(movieId)

}