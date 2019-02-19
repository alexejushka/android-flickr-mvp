package com.ap.flickr.network

import com.ap.flickr.BuildConfig
import com.ap.flickr.kodein.NetworkInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient : NetworkInterface {

    private val api: Api

    init {
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ChuckInterceptor(context)).build();
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun changeBaseUrl(url: String) {
        //mInterceptor.setHost(baseUrl);
    }

    override fun getApi(): Api {
        return api
    }

    companion object {
        const val API_KEY = "cceece829f07c59816bf44a53c649404"
    }

}
