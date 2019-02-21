package com.ap.flickr.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ap.flickr.PhotosContract
import com.ap.flickr.R
import com.ap.flickr.databinding.RItemBinding
import com.ap.flickr.pojo.Photo

class PhotosRecyclerViewAdapter internal constructor(
    private var photos: List<Photo>,
    private val presenter: PhotosContract.Presenter) : RecyclerView.Adapter<PhotosRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<RItemBinding>(LayoutInflater.from(parent.context), R.layout.r_item, parent, false)
        binding.presenter = presenter
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    fun setPhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(private val binding: RItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) {
            binding.photo = item
        }
    }

}