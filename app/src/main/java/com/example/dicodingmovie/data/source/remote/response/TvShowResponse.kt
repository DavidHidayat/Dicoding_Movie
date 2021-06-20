package com.example.dicodingmovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
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
) : Parcelable