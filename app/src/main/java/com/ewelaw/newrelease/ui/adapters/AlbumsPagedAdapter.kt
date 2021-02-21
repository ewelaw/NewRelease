package com.ewelaw.newrelease.ui.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewelaw.newrelease.R
import com.ewelaw.newrelease.databinding.ItemAlbumBinding
import com.ewelaw.newrelease.model.Album
import com.ewelaw.newrelease.utils.loadPictureWithGlide

class AlbumsPagedAdapter :
    PagingDataAdapter<Album, AlbumsPagedAdapter.AlbumsViewHolder>(AlbumsComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsViewHolder {
        return AlbumsViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindAlbum(it) }
    }

    inner class AlbumsViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindAlbum(item: Album) = with(binding) {
            ivCover.loadPictureWithGlide(item.images[0].url)
            tvArtists.text = item.artists[0].name
            tvReleaseDate.text = item.release_date
            clItemAlbum.transitionName = item.id

            this.clItemAlbum.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    clItemAlbum to "${item.id}"
                )
                val bundle = Bundle().also { bundle ->
                    bundle.putSerializable("album", item)
                }
                it.findNavController().navigate(
                    R.id.action_albumsFragment_to_albumDetailsFragment, bundle, null, extras
                )
            }

        }
    }
}

object AlbumsComparator : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}
