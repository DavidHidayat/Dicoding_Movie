package com.example.dicodingmovie.data.source.remote.response

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MoviesResponse.json").toString())

            val listArray = responseObject.getJSONArray("results")

            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val adult = movie.getBoolean("adult")
                val backdrop_path = movie.getString("backdrop_path")
                val genre_list = movie.getJSONArray("genre_ids")
                val id = movie.getInt("id")
                val original_language = movie.getString("original_language")
                val original_title = movie.getString("original_title")
                val overview = movie.getString("overview")
                val popularity = movie.getDouble("popularity")
                val poster_path = movie.getString("poster_path")
                val release_date = movie.getString("release_date")
                val title = movie.getString("title")
                val video = movie.getBoolean("video")
                val vote_average = movie.getDouble("vote_average")
                val vote_count = movie.getInt("vote_count")

                val genre_ids = ArrayList<Int>()
                for (x in 0 until genre_list.length()) {
                    genre_ids.add(genre_list.getInt(x))
                }

                val movieResponse = MovieResponse(adult,backdrop_path,genre_ids,id,original_language,original_title,overview,popularity,poster_path,release_date, title, video, vote_average, vote_count)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadMovie(movieId: Int): List<MovieResponse> {
        val fileName = String.format("Movie_%s.json", movieId)
        val list = ArrayList<MovieResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("movie")
                for (i in 0 until listArray.length()) {
                    val movie = listArray.getJSONObject(i)

                    val adult = movie.getBoolean("adult")
                    val backdrop_path = movie.getString("backdrop_path")
                    val genre_list = movie.getJSONArray("genre_ids")
                    val id = movie.getInt("id")
                    val original_language = movie.getString("original_language")
                    val original_title = movie.getString("original_title")
                    val overview = movie.getString("overview")
                    val popularity = movie.getDouble("popularity")
                    val poster_path = movie.getString("poster_path")
                    val release_date = movie.getString("release_date")
                    val title = movie.getString("title")
                    val video = movie.getBoolean("video")
                    val vote_average = movie.getDouble("vote_average")
                    val vote_count = movie.getInt("vote_count")

                    val genre_ids = ArrayList<Int>()
                    for (x in 0 until genre_list.length()) {
                        genre_ids.add(genre_list.getInt(x))
                    }

                    val movieResponse = MovieResponse(adult,backdrop_path,genre_ids,id,original_language,original_title,overview,popularity,poster_path,release_date, title, video, vote_average, vote_count)
                    list.add(movieResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShows(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tv_show = listArray.getJSONObject(i)

                val backdrop_path = tv_show.getString("backdrop_path")
                val first_air_date = tv_show.getString("first_air_date")
                val id = tv_show.getInt("id")
                val name = tv_show.getString("name")
                val original_language = tv_show.getString("original_language")
                val original_name = tv_show.getString("original_name")
                val overview = tv_show.getString("overview")
                val popularity = tv_show.getDouble("popularity")
                val poster_path = tv_show.getString("poster_path")
                val vote_average = tv_show.getDouble("vote_average")
                val vote_count = tv_show.getInt("vote_count")

                val tvShowResponse = TvShowResponse(backdrop_path,first_air_date, id, name, original_language, original_name, overview, popularity, poster_path, vote_average, vote_count)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(tvShowId: Int): List<TvShowResponse> {
        val fileName = String.format("Tv_Show_%s.json", tvShowId)
        val list = ArrayList<TvShowResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("tv_show")
                for (i in 0 until listArray.length()) {
                    val tv_show = listArray.getJSONObject(i)

                    val backdrop_path = tv_show.getString("backdrop_path")
                    val first_air_date = tv_show.getString("first_air_date")
                    val id = tv_show.getInt("id")
                    val name = tv_show.getString("name")
                    val original_language = tv_show.getString("original_language")
                    val original_name = tv_show.getString("original_name")
                    val overview = tv_show.getString("overview")
                    val popularity = tv_show.getDouble("popularity")
                    val poster_path = tv_show.getString("poster_path")
                    val vote_average = tv_show.getDouble("vote_average")
                    val vote_count = tv_show.getInt("vote_count")

                    val tvShowResponse = TvShowResponse(backdrop_path,first_air_date, id, name, original_language, original_name, overview, popularity, poster_path, vote_average, vote_count)
                    list.add(tvShowResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}