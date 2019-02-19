package com.ap.flickr.network

import com.ap.flickr.pojo.Photo

interface OnPhotosLoader {
    fun onSuccessPhotoLoad(photos: List<Photo>)
    fun onErrorPhotoLoad(message: String)
}
