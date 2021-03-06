package com.example.dicodingmovie.ui.tvshow.detail

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
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.databinding.ActivityDetailTvShowBinding
import com.example.dicodingmovie.databinding.ContentDetailTvShowBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val TV_SHOW_ID = "tv_show_id"
        const val TV_SHOW_TITLE = "tv_show_title"
    }

    private lateinit var detailTvShowBinding: ContentDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        detailTvShowBinding = activityDetailTvShowBinding.detailTvShow

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        val adapter = DetailTvShowAdapter()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(TV_SHOW_ID)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                detailTvShowBinding.progressBar.visibility = View.VISIBLE
                viewModel.getTvShows().observe(this, { tvShows ->
                    detailTvShowBinding.progressBar.visibility = View.GONE
                    adapter.setTvShows(tvShows)
//                    populateTvShow(viewModel.getTvShow())
                })
            }
            val tvShowTitle = extras.getString(TV_SHOW_TITLE)
            if (tvShowTitle != null) {
                actionBar!!.title = tvShowTitle
            }
        }

        with(detailTvShowBinding.rvTvShows) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        detailTvShowBinding.tvTitle.text = tvShowEntity.name
        detailTvShowBinding.tvOverview.text = tvShowEntity.overview
        detailTvShowBinding.tvFirstAirDate.text = tvShowEntity.first_air_date

        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w500"+tvShowEntity.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailTvShowBinding.imagePoster)
    }
}