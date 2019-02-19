package com.ap.flickr.pojo

data class PhotoList(val page: Int, val pages: Int, val photo: List<Photo>)

data class PhotoListWrapper(val photos: PhotoList)