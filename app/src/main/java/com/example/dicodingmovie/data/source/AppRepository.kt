package com.example.dicodingmovie.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.data.source.remote.RemoteDataSource
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) : AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object :RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
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
                        response.vote_count
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }
    override fun getMovie(movieId:Int): LiveData<MovieEntity> {
        val movieResults = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovie(movieId,object :RemoteDataSource.LoadMovieCallback{
            override fun onMovieReceived(response: MovieResponse?) {
                lateinit var movie: MovieEntity
                if (response != null) {
                    movie = MovieEntity(response.adult,
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
                        response.vote_count)
                    movieResults.postValue(movie)
                }
            }
        })
        return movieResults
    }
    override fun getOthersMovies(movieId:Int): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getOtherMovies(movieId,object :RemoteDataSource.LoadOthersMoviesCallback{
            override fun onOthersMoviesReceived(movieResponses: List<MovieResponse>) {
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
                        response.vote_count
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
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
                        response.vote_count
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }
    override fun getTvShow(tvShowId:Int): LiveData<TvShowEntity> {
        val tvShowResults = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShow(tvShowId, object : RemoteDataSource.LoadTvShowCallback{
            override fun onTvShowReceived(response: TvShowResponse?) {
                lateinit var tvShow: TvShowEntity
                if (response != null) {
                    tvShow = TvShowEntity(
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
                        response.vote_count
                    )
                    tvShowResults.postValue(tvShow)
                }
            }
        })
        return tvShowResults
    }
    override fun getOthersTvShows(tvShowId:Int): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getOtherTvShows(tvShowId,object : RemoteDataSource.LoadOthersTvShowsCallback{
            override fun onOthersTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
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
                        response.vote_count
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }
}