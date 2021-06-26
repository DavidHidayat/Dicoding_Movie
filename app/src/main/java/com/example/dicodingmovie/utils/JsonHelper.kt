package com.example.dicodingmovie.utils

import android.content.Context
import com.example.dicodingmovie.data.source.remote.response.MovieResponse
import com.example.dicodingmovie.data.source.remote.response.TvShowResponse
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
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val adult = movie.getBoolean("adult")
                val backdrop_path = movie.getString("backdrop_path")
                val genre_ids_list = movie.getJSONArray("genre_ids")
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
                for (x in 0 until genre_ids_list.length()) {
                    genre_ids.add(genre_ids_list.getInt(x))
                }
                val movieResponse = MovieResponse(adult, backdrop_path, genre_ids, id, original_language,original_title,overview,popularity,poster_path,release_date,title, video, vote_average, vote_count)
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
            val responseObject = JSONObject(parsingFileToString(fileName).toString())
            val adult = responseObject.getBoolean("adult")
            val backdrop_path = responseObject.getString("backdrop_path")
            val genre_ids_list = responseObject.getJSONArray("genre_ids")
            val id = responseObject.getInt("id")
            val original_language = responseObject.getString("original_language")
            val original_title = responseObject.getString("original_title")
            val overview = responseObject.getString("overview")
            val popularity = responseObject.getDouble("popularity")
            val poster_path = responseObject.getString("poster_path")
            val release_date = responseObject.getString("release_date")
            val title = responseObject.getString("title")
            val video = responseObject.getBoolean("video")
            val vote_average = responseObject.getDouble("vote_average")
            val vote_count = responseObject.getInt("vote_count")

            val genre_ids = ArrayList<Int>()
            for (x in 0 until genre_ids_list.length()) {
                genre_ids.add(genre_ids_list.getInt(x))
            }
            val data = MovieResponse(adult, backdrop_path, genre_ids, id, original_language,original_title,overview,popularity,poster_path,release_date,title, video, vote_average, vote_count)
            list.add(data)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadOtherMovies(movieId: Int): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                if (movieId != id){
                    val adult = movie.getBoolean("adult")
                    val backdrop_path = movie.getString("backdrop_path")
                    val genre_ids_list = movie.getJSONArray("genre_ids")
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
                    for (x in 0 until genre_ids_list.length()) {
                        genre_ids.add(genre_ids_list.getInt(x))
                    }
                    val movieResponse = MovieResponse(adult, backdrop_path, genre_ids, id, original_language,original_title,overview,popularity,poster_path,release_date,title, video, vote_average, vote_count)
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
                val tvShow = listArray.getJSONObject(i)

                val backdrop_path = tvShow.getString("backdrop_path")
                val first_air_date = tvShow.getString("first_air_date")
                val id = tvShow.getInt("id")
                val name = tvShow.getString("name")
                val original_language = tvShow.getString("original_language")
                val original_name = tvShow.getString("original_name")
                val overview = tvShow.getString("overview")
                val popularity = tvShow.getDouble("popularity")
                val poster_path = tvShow.getString("poster_path")
                val vote_average = tvShow.getDouble("vote_average")
                val vote_count = tvShow.getInt("vote_count")

                val tvShowResponse = TvShowResponse(backdrop_path,first_air_date,id,name,original_language,original_name,overview,popularity,poster_path, vote_average, vote_count)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(tvShowId: Int): List<TvShowResponse> {
        val fileName = String.format("TvShow_%s.json", tvShowId)
        val list = ArrayList<TvShowResponse>()
        try {

            val responseObject = JSONObject(parsingFileToString(fileName).toString())
            val backdrop_path = responseObject.getString("backdrop_path")
            val first_air_date = responseObject.getString("first_air_date")
            val id = responseObject.getInt("id")
            val name = responseObject.getString("name")
            val original_language = responseObject.getString("original_language")
            val original_name = responseObject.getString("original_name")
            val overview = responseObject.getString("overview")
            val popularity = responseObject.getDouble("popularity")
            val poster_path = responseObject.getString("poster_path")
            val vote_average = responseObject.getDouble("vote_average")
            val vote_count = responseObject.getInt("vote_count")

            val data = TvShowResponse(backdrop_path,first_air_date,id,name,original_language,original_name,overview,popularity,poster_path, vote_average, vote_count)
            list.add(data)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


    fun loadOtherTvShows(tvShowId: Int): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                if (tvShowId != tvShow.getInt("id")){
                    val backdrop_path = tvShow.getString("backdrop_path")
                    val first_air_date = tvShow.getString("first_air_date")
                    val id = tvShow.getInt("id")
                    val name = tvShow.getString("name")
                    val original_language = tvShow.getString("original_language")
                    val original_name = tvShow.getString("original_name")
                    val overview = tvShow.getString("overview")
                    val popularity = tvShow.getDouble("popularity")
                    val poster_path = tvShow.getString("poster_path")
                    val vote_average = tvShow.getDouble("vote_average")
                    val vote_count = tvShow.getInt("vote_count")

                    val tvShowResponse = TvShowResponse(backdrop_path,first_air_date,id,name,original_language,original_name,overview,popularity,poster_path, vote_average, vote_count)
                    list.add(tvShowResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

}