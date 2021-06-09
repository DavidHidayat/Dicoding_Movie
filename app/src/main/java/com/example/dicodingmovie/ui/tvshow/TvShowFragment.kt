package com.example.dicodingmovie.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingmovie.R
import com.example.dicodingmovie.data.TvShowEntity
import com.example.dicodingmovie.databinding.FragmentTvShowBinding
import com.example.dicodingmovie.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(),TvShowFragmentCallback {

    lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tv_show = viewModel.getTvShows()
            val adapter = TvShowAdapter(this)
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(requireActivity(), { tvShows ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                adapter.setTvShow(tvShows)
                adapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(tv_show: TvShowEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText(resources.getString(R.string.share_text, tv_show.name))
                .startChooser()
        }
    }
}