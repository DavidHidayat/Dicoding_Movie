package com.example.dicodingmovie.data

data class TvShowEntity(
    var backdropPath:String,
    var firstAirDate:String,
    var id : Int,
    var name : String,
    var originalLanguage : String,
    var originalName : String,
    val overview : String,
    var popularity : Double,
    var posterPath : String,
    val voteAverage : Double,
    val voteCount : Int
    )