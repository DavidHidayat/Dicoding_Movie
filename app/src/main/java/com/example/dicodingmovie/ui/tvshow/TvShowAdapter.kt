package com.example.dicodingmovie.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.source.local.entity.TvShowEntity
import com.example.dicodingmovie.databinding.ItemsTvShowBinding
import com.example.dicodingmovie.ui.tvshow.detail.DetailTvShowActivity

class TvShowAdapter(private val callback: TvShowFragmentCallback) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShows = ArrayList<TvShowEntity>()

    fun setTvShow(tv_shows: List<TvShowEntity>?) {
        if (tv_shows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tv_shows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tv_show = listTvShows[position]
        holder.bind(tv_show)
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv_show: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tv_show.name
                tvFirstAirDate.text = tv_show.firstAirDate.substring(0,4)
                tvItemOverview.text = if(tv_show.overview.length > 30 ) tv_show.overview.substring(0,25)+" ...Read More" else tv_show.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.TV_SHOW_ID, tv_show.id)
                    intent.putExtra(DetailTvShowActivity.TV_SHOW_TITLE, tv_show.name)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(tv_show) }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.image_base_url,tv_show.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}