package com.example.dicodingmovie.ui.tvshowfavorite.detail

import android.content.Intent
import android.os.Bundle
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
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.databinding.ActivityDetailTvShowFavoriteBinding
import com.example.dicodingmovie.databinding.ContentDetailTvShowFavoriteBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.example.dicodingmovie.vo.Status

class DetailTvShowFavoriteActivity : AppCompatActivity() {


    private lateinit var activity: ActivityDetailTvShowFavoriteBinding
    private lateinit var content: ContentDetailTvShowFavoriteBinding

    private lateinit var viewModel: DetailTvShowFavoriteViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = ActivityDetailTvShowFavoriteBinding.inflate(layoutInflater)
        content = activity.detailTvShowFavorite

        setContentView(activity.root)

        setSupportActionBar(activity.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowFavoriteViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(TV_SHOW_ID)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                viewModel.tvShowById.observe(this, { tvShows ->
                    if (tvShows != null) {
                        when (tvShows.status) {
                            Status.LOADING -> content?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvShows.data != null) {
                                content?.progressBar?.visibility = View.GONE
                                populateTvShow(tvShows.data)
                                setButtonNextPrevState()
                            }
                            Status.ERROR -> {
                                content?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    content?.btnNext?.setOnClickListener {
                        viewModel.setNextPage.observe(this, { tvShow ->
                            var state: Boolean = false
                            if (tvShow != null) {
                                val intent = Intent(this, DetailTvShowFavoriteActivity::class.java)
                                intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_ID, tvShow.id)
                                intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_TITLE, tvShow.name)
                                this.startActivity(intent)
                            }
                        })
                    }
                    content?.btnPrev?.setOnClickListener {
                        viewModel.setPrevPage.observe(this, { tvShow ->
                            var state: Boolean = false
                            if (tvShow != null) {
                                val intent = Intent(this, DetailTvShowFavoriteActivity::class.java)
                                intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_ID, tvShow.id)
                                intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_TITLE, tvShow.name)
                                this.startActivity(intent)
                            }
                        })
                    }

                })
            }

            val tvShowTitle = extras.getString(TV_SHOW_TITLE)
            if (tvShowTitle != null) {
                actionBar?.title = tvShowTitle
            }
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        content.tvTitle.text = tvShowEntity.name
        content.tvOverview.text = tvShowEntity.overview
        content.tvFirstAirDate.text = tvShowEntity.firstAirDate

        Glide.with(this)
            .load(resources.getString(R.string.image_base_url,tvShowEntity.posterPath))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(content.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.tvShowFavoriteById.observe(this, { tvShow ->
            var state: Boolean = false
            if (tvShow != null) {
                state = true
            }
            setfavoritState(state)
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorit) {
            viewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setfavoritState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorit)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }

    private fun setButtonNextPrevState() {
        if (activity != null) {
            viewModel.setNextPage.observe(this, { tvShow ->
                if (tvShow != null) {
                    content?.btnNext?.isEnabled = true
                }else{
                    content?.btnNext?.isEnabled = false
                }
            })
            viewModel.setPrevPage.observe(this, { tvShow ->
                if (tvShow != null) {
                    content?.btnPrev?.isEnabled = true
                }else{
                    content?.btnPrev?.isEnabled = false
                }
            })

        }
    }

    companion object {
        const val TV_SHOW_ID = "tv_show_id"
        const val TV_SHOW_TITLE = "tv_show_title"
    }

}