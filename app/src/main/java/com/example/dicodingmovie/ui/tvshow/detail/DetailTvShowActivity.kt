package com.example.dicodingmovie.ui.tvshow.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.databinding.ActivityDetailTvShowBinding
import com.example.dicodingmovie.databinding.ContentDetailTvShowBinding
import com.example.dicodingmovie.utils.DataDummy

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

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(TV_SHOW_ID)
            if (tvShowId != null) {
                val tv_shows = DataDummy.generateDummyTvShow()
                adapter.setTvShows(tv_shows,tvShowId)
                for (tv_show in DataDummy.generateDummyTvShow()) {
                    if (tv_show.id  == tvShowId) {
                        populateTvShow(tv_show)
                    }
                }
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