package com.example.dicodingmovie.ui.movie.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.databinding.ActivityDetailMovieBinding
import com.example.dicodingmovie.databinding.ContentDetailMovieBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.example.dicodingmovie.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var detailContentBinding: ContentDetailMovieBinding

    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailMovie

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        val adapter = DetailMovieAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                viewModel.movieById.observe(this, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> detailContentBinding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movies.data != null) {
                                detailContentBinding?.progressBar?.visibility = View.GONE
                                populateMovie(movies.data)
                            }
                            Status.ERROR -> {
                                detailContentBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
                viewModel.getOthersMovies.observe(this, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> detailContentBinding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movies.data != null) {
                                detailContentBinding?.progressBar?.visibility = View.GONE
                                adapter.setMovies(movies.data)
                                adapter.notifyDataSetChanged()
                            }
                            Status.ERROR -> {
                                detailContentBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }

            val movieTitle = extras.getString(MOVIE_TITLE)
            if (movieTitle != null) {
                actionBar?.title = movieTitle
            }
        }

        with(detailContentBinding?.rvMovies) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailMovieActivity)
            setHasFixedSize(true)
            this?.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.tvTitle.text = movieEntity.title
        detailContentBinding.tvOverview.text = movieEntity.overview
        detailContentBinding.tvReleaseDate.text = movieEntity.releaseDate

        Glide.with(this)
            .load(resources.getString(R.string.image_base_url,movieEntity.posterPath))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.movieById.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> detailContentBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        detailContentBinding.progressBar.visibility = View.GONE
                        val state = movie.data.bookmarked
                        setBookmarkState(state)
                    }
                    Status.ERROR -> {
                        detailContentBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }
}
