package com.example.dicodingmovie.ui.tvshow.detail

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
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.databinding.ActivityDetailTvShowBinding
import com.example.dicodingmovie.databinding.ContentDetailTvShowBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory
import com.example.dicodingmovie.vo.Status

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var activityDetailTvShowBinding : ActivityDetailTvShowBinding

    private lateinit var detailTvShowBinding: ContentDetailTvShowBinding

    private lateinit var viewModel: DetailTvShowViewModel
    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        detailTvShowBinding = activityDetailTvShowBinding.detailTvShow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        val adapter = DetailTvShowAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val tvShowId = extras.getInt(TV_SHOW_ID)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                viewModel.tvShowById.observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> detailTvShowBinding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvShow.data != null) {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                populateTvShow(tvShow.data)
                            }
                            Status.ERROR -> {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
                viewModel.getOthersTvShows.observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> detailTvShowBinding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvShow.data != null) {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                adapter.setTvShows(tvShow.data)
                                adapter.notifyDataSetChanged()
                            }
                            Status.ERROR -> {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            val tvShowTitle = extras.getString(TV_SHOW_TITLE)
            if (tvShowTitle != null) {
                actionBar?.title = tvShowTitle
            }
        }

        with(detailTvShowBinding?.rvTvShows) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
            setHasFixedSize(true)
            this?.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        detailTvShowBinding.tvTitle.text = tvShowEntity.name
        detailTvShowBinding.tvOverview.text = tvShowEntity.overview
        detailTvShowBinding.tvFirstAirDate.text = tvShowEntity.firstAirDate

        Glide.with(this)
            .load(resources.getString(R.string.image_base_url,tvShowEntity.posterPath))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailTvShowBinding.imagePoster)
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

    companion object {
        const val TV_SHOW_ID = "tv_show_id"
        const val TV_SHOW_TITLE = "tv_show_title"
    }

}