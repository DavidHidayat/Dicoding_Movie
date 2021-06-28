package com.example.dicodingmovie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowfaventities")
data class TvShowFavoriteEntity(
    @ColumnInfo(name = "backdropPath")
    var backdropPath:String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate:String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage : String,

    @ColumnInfo(name = "originalName")
    var originalName : String,

    @ColumnInfo(name = "overview")
    val overview : String,

    @ColumnInfo(name = "popularity")
    var popularity : Double,

    @ColumnInfo(name = "posterPath")
    var posterPath : String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage : Double,

    @ColumnInfo(name = "voteCount")
    val voteCount : Int
)