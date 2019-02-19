package com.ap.flickr

import android.app.Application
import android.content.Context
import com.ap.flickr.kodein.NetworkInterface
import com.ap.flickr.kodein.PhotosInteractor
import com.ap.flickr.network.RestClient
import com.ap.flickr.utils.OnSaveInterface
import com.ap.flickr.utils.SharedPrefsHelper
import com.github.salomonbrys.kodein.*

class FlickrApp : Application() {

    lateinit var appModule: Kodein

    override fun onCreate() {
        super.onCreate()
        appModule = mainComponent(this)
    }

    //needed several modules, of course
    fun mainComponent(app: FlickrApp) = Kodein {
        bind<Context>() with instance(app)
        bind<NetworkInterface>() with singleton { RestClient() }
        bind<PhotosContract.Interactor>() with provider { PhotosInteractor(instance(), instance()) }
        bind<OnSaveInterface>() with singleton { SharedPrefsHelper(instance()) }
    }

}