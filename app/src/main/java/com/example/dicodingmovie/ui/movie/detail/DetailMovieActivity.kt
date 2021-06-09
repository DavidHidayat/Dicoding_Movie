package com.example.dicodingmovie.ui.movie.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.MovieEntity
import com.example.dicodingmovie.databinding.ActivityDetailMovieBinding
import com.example.dicodingmovie.databinding.ContentDetailMovieBinding
import com.example.dicodingmovie.utils.DataDummy
import com.example.dicodingmovie.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailMovie

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        val adapter = DetailMovieAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                detailContentBinding.progressBar.visibility = View.VISIBLE
                viewModel.getMovies().observe(this, { movies ->
                    detailContentBinding.progressBar.visibility = View.GONE
//                    populateMovie(viewModel.getMovie())
                    adapter.setMovies(movies)
                    adapter.notifyDataSetChanged()
                })


            }
            val movieTitle = extras.getString(MOVIE_TITLE)
            if (movieTitle != null) {
                actionBar!!.title = movieTitle
            }
        }

        with(detailContentBinding.rvMovies) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailMovieActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.tvTitle.text = movieEntity.title
        detailContentBinding.tvOverview.text = movieEntity.overview
        detailContentBinding.tvReleaseDate.text = movieEntity.release_date

        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500"+movieEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)
    }
}