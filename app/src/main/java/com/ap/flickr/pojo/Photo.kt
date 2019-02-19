package com.ap.flickr.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val id: String, val secret: String, val server: String, val farm: Int,
                 val title: String, val url_o: String, val url_m: String?) : Parcelable