package com.example.dicodingmovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.MovieFavoriteEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id != :movieId")
    fun getOthersMovies(movieId: Int):LiveData<List<MovieEntity>>

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
    fun getTvShows():LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities WHERE id != :tvShowId")
    fun getOthersTvShows(tvShowId:Int):LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities WHERE id = :tvShowId")
    fun getTvShowById(tvShowId:Int):LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

}