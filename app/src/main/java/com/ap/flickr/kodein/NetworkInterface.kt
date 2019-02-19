package com.ap.flickr.kodein

import com.ap.flickr.network.Api

interface NetworkInterface {
    fun getApi() : Api
}