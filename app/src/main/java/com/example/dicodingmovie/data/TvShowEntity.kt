package com.example.dicodingmovie.data

data class TvShowEntity(
    var backdrop_path:String,
    var first_air_date:String,
    var id : Int,
    var name : String,
    var original_language : String,
    var original_name : String,
    val overview : String,
    var popularity : Double,
    var poster_path : String,
    val vote_average : Double,
    val vote_count : Int
    )