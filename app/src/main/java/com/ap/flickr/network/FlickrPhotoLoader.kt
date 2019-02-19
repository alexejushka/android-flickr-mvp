package com.ap.flickr.network

import com.ap.flickr.pojo.PhotoListWrapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FlickrPhotoLoader(val api: Api, val loader: OnPhotosLoader) : LoaderInterface<PhotoListWrapper> {

    override fun rxAction(vararg args: String): Disposable {
        val flowable = makeRetrofitRequest(args.component1())
        return flowable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .flatMapIterable { wrapper -> wrapper.photos.photo }
            .filter { photo -> !photo.url_m.isNullOrEmpty() }
            .toList()
            .subscribe(
                { photos ->
                    loader.onSuccessPhotoLoad(photos)
                }, { throwable ->
                    loader.onErrorPhotoLoad("Error $throwable.message")
                })
    }

    override fun makeRetrofitRequest(vararg args: String): Flowable<PhotoListWrapper> {
        val apiKey = RestClient.API_KEY
        val extras = "url_m"
        val format = "json"
        val noJsonCallback = 1
        val countPerPage = 50
        val searchText = args[0]
        return api.getPhotoList(apiKey, searchText, extras, format, countPerPage, noJsonCallback)
    }
}