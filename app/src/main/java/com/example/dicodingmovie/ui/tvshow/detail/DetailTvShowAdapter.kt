package com.example.dicodingmovie.ui.tvshow.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.databinding.ItemsMovieListBinding
import com.example.dicodingmovie.databinding.ItemsTvShowListBinding

class DetailTvShowAdapter : RecyclerView.Adapter<DetailTvShowAdapter.TvShowViewHolder>() {

    private val listTvShows = ArrayList<TvShowEntity>()

    fun setTvShows(tv_shows: List<TvShowEntity>?) {
        if (tv_shows == null) return
        this.listTvShows.clear()
        for (tv_show in tv_shows) {
            this.listTvShows.add(tv_show)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowListBinding = ItemsTvShowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowListBinding)
    }

    override fun onBindViewHolder(viewHolder: TvShowViewHolder, position: Int) {
        val tv_show = listTvShows[position]
        viewHolder.bind(tv_show)
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv_show: TvShowEntity) {
            binding.textTvShowTitle.text = tv_show.name
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,  DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.TV_SHOW_ID, tv_show.id)
                intent.putExtra(DetailTvShowActivity.TV_SHOW_TITLE, tv_show.name)
                itemView.context.startActivity(intent)
            }

        }
    }
}