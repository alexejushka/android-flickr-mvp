package com.ap.flickr.ui

import com.ap.flickr.PhotosContract
import com.ap.flickr.network.OnPhotosLoader
import com.ap.flickr.pojo.Photo
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance

class PhotosPresenter(kodein: Kodein, val view: PhotosContract.View) : PhotosContract.Presenter, OnPhotosLoader {

    private val interactor: PhotosContract.Interactor = kodein.instance()

    override fun openPhoto(photo: Photo) {
        this.view.shopPreviewDetail(photo)
    }

    override fun onSuccessPhotoLoad(photos: List<Photo>) {
        view.setProgressIndicator(false)
        view.showPhotos(photos)
    }

    override fun onErrorPhotoLoad(message: String) {
        view.setProgressIndicator(false)
        view.showError(message)
    }

    override fun loadPhotos(search: String) {
        view.updateAutoComplete(interactor.saveSuggest(search))
        view.setProgressIndicator(true)

        interactor.loadPhotos(search, this)
    }

    override fun start() {
        view.updateAutoComplete(interactor.getSuggests())
    }

    override fun stop() {}
}