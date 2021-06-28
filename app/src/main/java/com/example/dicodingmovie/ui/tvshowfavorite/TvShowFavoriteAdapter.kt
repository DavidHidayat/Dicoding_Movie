package com.example.dicodingmovie.ui.tvshowfavorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.TvShowFavoriteEntity
import com.example.dicodingmovie.databinding.ItemsTvShowBinding
import com.example.dicodingmovie.ui.tvshow.detail.DetailTvShowActivity
import com.example.dicodingmovie.ui.tvshowfavorite.detail.DetailTvShowFavoriteActivity

class TvShowFavoriteAdapter : PagedListAdapter<TvShowFavoriteEntity, TvShowFavoriteAdapter.TvShowsViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowFavoriteEntity>() {
            override fun areItemsTheSame(oldItem: TvShowFavoriteEntity, newItem: TvShowFavoriteEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowFavoriteEntity, newItem: TvShowFavoriteEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowFavoriteEntity? = getItem(swipedPosition)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsTvShowBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShows = getItem(position)
        if (tvShows != null) {
            holder.bind(tvShows)
        }
    }

    class TvShowsViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowFavoriteEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvFirstAirDate.text = tvShow.firstAirDate.substring(0,4)
                tvItemOverview.text = if(tvShow.overview.length > 30 ) tvShow.overview.substring(0,25)+" ...Read More" else tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowFavoriteActivity::class.java)
                    intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_ID, tvShow.id)
                    intent.putExtra(DetailTvShowFavoriteActivity.TV_SHOW_TITLE, tvShow.name)
                    itemView.context.startActivity(intent)
                }
                imgShare.visibility = View.GONE
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.image_base_url,tvShow.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)

            }
        }
    }
}