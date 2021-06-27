package com.example.dicodingmovie.ui.moviefavorite.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.MovieEntity
import com.example.dicodingmovie.databinding.ActivityDetailMovieFavoriteBinding
import com.example.dicodingmovie.databinding.ContentDetailMovieFavoriteBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.example.dicodingmovie.vo.Status

class DetailMovieFavoriteActivity : AppCompatActivity() {


    private lateinit var activity: ActivityDetailMovieFavoriteBinding
    private lateinit var content: ContentDetailMovieFavoriteBinding

    private lateinit var viewModel: DetailMovieFavoriteViewModel
    private var menu: Menu? = null

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ADA GAK ","Ssss")

        activity = ActivityDetailMovieFavoriteBinding.inflate(layoutInflater)
        content = activity.detailMovieFavorite

        setContentView(activity.root)

        setSupportActionBar(activity.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieFavoriteViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(MOVIE_ID)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                viewModel.movieById.observe(this, { movies ->
                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> content?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (movies.data != null) {
                                content?.progressBar?.visibility = View.GONE
                                populateMovie(movies.data)
                                setButtonNextPrevState()
                            }
                            Status.ERROR -> {
                                content?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    content?.btnNext?.setOnClickListener {
                        viewModel.setNextPage.observe(this, { movie ->
                            var state: Boolean = false
                            if (movie != null) {
                                val intent = Intent(this, DetailMovieFavoriteActivity::class.java)
                                intent.putExtra(DetailMovieFavoriteActivity.MOVIE_ID, movie.id)
                                intent.putExtra(DetailMovieFavoriteActivity.MOVIE_TITLE, movie.title)
                                this.startActivity(intent)
                            }
                        })
                    }
                    content?.btnPrev?.setOnClickListener {
                        viewModel.setPrevPage.observe(this, { movie ->
                            var state: Boolean = false
                            if (movie != null) {
                                val intent = Intent(this, DetailMovieFavoriteActivity::class.java)
                                intent.putExtra(DetailMovieFavoriteActivity.MOVIE_ID, movie.id)
                                intent.putExtra(DetailMovieFavoriteActivity.MOVIE_TITLE, movie.title)
                                this.startActivity(intent)
                            }else{
                                Log.e("Kosong nig","sss")
                            }
                        })
                    }

                })
            }

            val movieTitle = extras.getString(MOVIE_TITLE)
            if (movieTitle != null) {
                actionBar?.title = movieTitle
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        content.tvTitle.text = movieEntity.title
        content.tvOverview.text = movieEntity.overview
        content.tvReleaseDate.text = movieEntity.releaseDate

        Glide.with(this)
            .load(resources.getString(R.string.image_base_url,movieEntity.posterPath))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(content.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.movieFavoriteById.observe(this, { movie ->
            var state: Boolean = false
            if (movie != null) {
                state = true
            }
            setBookmarkState(state)
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setFavorite()
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

    private fun setButtonNextPrevState() {
        if (activity != null) {
            viewModel.setNextPage.observe(this, { movie ->
                if (movie != null) {
                    content?.btnNext?.isEnabled = true
                }else{
                    content?.btnNext?.isEnabled = false
                }
            })
            viewModel.setPrevPage.observe(this, { movie ->
                if (movie != null) {
                    content?.btnPrev?.isEnabled = true
                }else{
                    content?.btnPrev?.isEnabled = false
                }
            })

        }
    }
}