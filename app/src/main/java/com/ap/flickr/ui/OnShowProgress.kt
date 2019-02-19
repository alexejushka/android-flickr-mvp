package com.ap.flickr.ui

import com.ap.flickr.pojo.Photo

interface OnShowProgress {
    fun onShowProgress(show: Boolean)

    fun openPreview(photo: Photo)
}