package com.example.dicodingmovie.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieFavorite (
    @Embedded
    var mMovie: MovieEntity,
    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    var mMovieFav: List<MovieEntity>
)