package com.example.dicodingmovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM movieentities")
    fun getMovies():LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id != :movieId")
    fun getOthersMovies(movieId:Int):LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getMovieById(movieId:Int):LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(courses: List<MovieEntity>)

    @Update
    fun updateMovie(course: MovieEntity)

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows():LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id != :tvShowId")
    fun getOthersTvShows(tvShowId:Int):LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :tvShowId")
    fun getTvShowById(tvShowId:Int):LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(courses: List<TvShowEntity>)

    @Update
    fun updateTvShow(course: TvShowEntity)



}