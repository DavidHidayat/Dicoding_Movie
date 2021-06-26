package com.example.dicodingmovie.ui.tvshow.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                viewModel.getTvShow().observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> detailTvShowBinding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> if (tvShow.data != null) {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                populateTvShow(tvShow.data[0])
                            }
                            Status.ERROR -> {
                                detailTvShowBinding?.progressBar?.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
                viewModel.getOthersTvShows().observe(this, { tvShow ->
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

    companion object {
        const val TV_SHOW_ID = "tv_show_id"
        const val TV_SHOW_TITLE = "tv_show_title"
    }

}