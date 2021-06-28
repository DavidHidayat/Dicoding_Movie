package com.example.dicodingmovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id != :movieId")
    fun getOthersMovies(movieId: Int):DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getMovieById(movieId:Int):LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM moviefaventites")
    fun getFavoritedMovie():DataSource.Factory<Int, MovieFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesFavorite(movies: List<MovieFavoriteEntity>)

    @Query("SELECT * FROM moviefaventites WHERE id = :movieId")
    fun getMovieFavoriteById(movieId:Int):LiveData<MovieFavoriteEntity>

    @Query("DELETE FROM moviefaventites WHERE id = :movieId")
    fun deleteMoviesFavorite(movieId:Int)

    @Query("SELECT * FROM moviefaventites where id = (select max(id) from moviefaventites where id < :movieId)")
    fun prevMovieFavorite(movieId: Int):LiveData<MovieFavoriteEntity>

    @Query("SELECT * FROM moviefaventites where id = (select min(id) from moviefaventites where id > :movieId)")
    fun nextMovieFavorite(movieId: Int):LiveData<MovieFavoriteEntity>


    @Query("UPDATE moviefaventites SET bookmarked = :bookmarked WHERE id = :movieId")
    fun updateMovie(movieId: Int, bookmarked: Boolean)

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows():DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE id != :tvShowId")
    fun getOthersTvShows(tvShowId:Int):DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE id = :tvShowId")
    fun getTvShowById(tvShowId:Int):LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM tvshowfaventities")
    fun getFavoritedTvShow():DataSource.Factory<Int, TvShowFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowsFavorite(tvShows: List<TvShowFavoriteEntity>)

    @Query("SELECT * FROM tvshowfaventities WHERE id = :tvShowId")
    fun getTvShowFavoriteById(tvShowId:Int):LiveData<TvShowFavoriteEntity>

    @Query("DELETE FROM tvshowfaventities WHERE id = :tvShowId")
    fun deleteTvShowsFavorite(tvShowId:Int)

    @Query("SELECT * FROM tvshowfaventities where id = (select max(id) from tvshowfaventities where id < :tvShowId)")
    fun prevTvShowFavorite(tvShowId: Int):LiveData<TvShowFavoriteEntity>

    @Query("SELECT * FROM tvshowfaventities where id = (select min(id) from tvshowfaventities where id > :tvShowId)")
    fun nextTvShowFavorite(tvShowId: Int):LiveData<TvShowFavoriteEntity>


}