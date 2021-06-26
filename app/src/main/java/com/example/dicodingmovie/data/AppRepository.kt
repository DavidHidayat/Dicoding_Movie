package com.example.dicodingmovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.source.local.LocalDataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
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
    private val appExecutors: AppExecutors) :
    AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData, localData, appExecutors).apply { instance = this }
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
                    val course = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.genreIds,
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
                    dataList.add(course)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }
    override fun getOthersMovies(movieId:Int): LiveData<Resource<List<MovieEntity>>> {
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
                    val course = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.genreIds,
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
                    dataList.add(course)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }
    override fun getMovie(movieId:Int): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getMovieById(movieId)
            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie(movieId)
            public override fun saveCallResult(responses: List<MovieResponse>) {
                val dataList = ArrayList<MovieEntity>()
                for (response in responses) {
                    val course = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.genreIds,
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
                    dataList.add(course)
                }

                localDataSource.insertMovies(dataList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
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
    override fun getTvShow(tvShowId: String): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
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
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
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
}