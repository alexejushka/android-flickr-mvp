package com.ap.flickr.network

import com.ap.flickr.pojo.PhotoListWrapper
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?method=flickr.photos.search")
    fun getPhotoList(
        @Query("api_key") apiKey: String,
        @Query("text") searchText: String,
        @Query("extras") extraParameters: String,
        @Query("format") responseFormat: String,
        @Query("per_page") count: Int,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Flowable<PhotoListWrapper>

    @GET("?method=flickr.photos.search")
    fun getPhotoForLocation(
        @Query("api_key") apiKey: String,
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("extras") extraParameters: String,
        @Query("format") responseFormat: String,
        @Query("per_page") count: Int,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Flowable<PhotoListWrapper>

}
