package com.example.dicodingmovie.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicodingmovie.data.source.local.LocalDataSource
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.data.source.remote.ApiResponse
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
import com.example.dicodingmovie.utils.AppExecutors
import com.example.dicodingmovie.vo.Resource
import java.util.*

class FakeAppRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    AppDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
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

    override fun getOthersMovies(movieId: Int): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getOthersMovies(movieId),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
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

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(responses: List<TvShowResponse>) {
                val dataList = ArrayList<TvShowEntity>()
                for (response in responses) {
                    val tvShow = TvShowEntity(
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
                    dataList.add(tvShow)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowById(tvShowId)

            public override fun saveCallResult(response: TvShowResponse) {
                val dataList = ArrayList<TvShowEntity>()
                if (response != null) {
                    val tTvShow = TvShowEntity(
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
                    dataList.add(tTvShow)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getOthersTvShows(tvShowId: Int): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getOthersTvShows(tvShowId), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getOtherTvShows(tvShowId)

            public override fun saveCallResult(responses: List<TvShowResponse>) {
                val dataList = ArrayList<TvShowEntity>()
                for (response in responses) {
                    val tvShow = TvShowEntity(
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
                    dataList.add(tvShow)
                }

                localDataSource.insertTvShows(dataList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovie(): LiveData<PagedList<MovieFavoriteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovie(), config).build()
    }

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

    override fun getFavoritedTvShow(): LiveData<PagedList<TvShowFavoriteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

    override fun getTvShowFavoriteById(tvShowId: Int): LiveData<TvShowFavoriteEntity> =
        localDataSource.getTvShowFavoriteById(tvShowId)

    override fun insertTvShowFavorite(tvShow: TvShowEntity) = appExecutors.diskIO().execute {
        val dataList = ArrayList<TvShowFavoriteEntity>()
        val tvShowData = TvShowFavoriteEntity(
            tvShow.backdropPath,
            tvShow.firstAirDate,
            tvShow.id,
            tvShow.name,
            tvShow.originalLanguage,
            tvShow.originalName,
            tvShow.overview,
            tvShow.popularity,
            tvShow.posterPath,
            tvShow.voteAverage,
            tvShow.voteCount
        )
        dataList.add(tvShowData)
        localDataSource.insertTvShowFavorite(dataList)

    }
    override fun deleteTvShowFavorite(tvShowId: Int) = appExecutors.diskIO().execute {
        localDataSource.deleteTvShowFavorite(
            tvShowId
        )
    }

    fun nextTvShowFavorite(tvShowId: Int): LiveData<TvShowFavoriteEntity> = localDataSource.nextTvShowFavorite(tvShowId)
    fun prevTvShowFavorite(tvShowId: Int): LiveData<TvShowFavoriteEntity> = localDataSource.prevTvShowFavorite(tvShowId)

}