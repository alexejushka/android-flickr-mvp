package com.ap.flickr.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ap.flickr.R
import com.ap.flickr.pojo.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.preview_fragment.*


class PreviewFragment : Fragment() {

    companion object {
        val TAG = PreviewFragment::class.java.simpleName
        const val KEY_PHOTO = "photo"

        fun newInstance(photo: Photo): PreviewFragment {
            return PreviewFragment().apply {
                arguments = Bundle().apply { putParcelable(KEY_PHOTO, photo) }
            }
        }
    }

    lateinit var listener: OnShowProgress

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnShowProgress) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnShowProgress")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val photo = arguments!!.getParcelable<Photo>(KEY_PHOTO)

        listener.onShowProgress(true)

        val bigImageUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.jpg"

        Glide.with(context!!).load(bigImageUrl).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
            ): Boolean {
                listener.onShowProgress(false);
                return false
            }

            override fun onResourceReady(
                resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
            ): Boolean {
                listener.onShowProgress(false)
                return false
            }
        }).into(preview)
    }

}