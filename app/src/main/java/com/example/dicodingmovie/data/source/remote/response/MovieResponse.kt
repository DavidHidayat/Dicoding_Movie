package com.example.dicodingmovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    var adult: Boolean,
    var backdropPath: String?,
    var genreIds: ArrayList<Int>,
    var id: Int,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
): Parcelable