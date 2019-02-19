package com.ap.flickr.network

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

/**
 * getting data from source
 * @param <T>
</T> */
interface LoaderInterface<T> {
    /**
     * use RXJava2 for listening events
     * @param args params for request
     * @return rxjava Disposable
     */
    fun rxAction(vararg args: String): Disposable

    fun makeRetrofitRequest(vararg args: String): Flowable<T>
}
