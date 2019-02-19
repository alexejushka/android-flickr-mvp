package com.ap.flickr

import com.ap.flickr.network.OnPhotosLoader
import com.ap.flickr.pojo.Photo
import com.example.android.architecture.blueprints.todoapp.BaseView

/**
 * the contract between view and presenter
 */
interface PhotosContract {

    interface View : BaseView<Presenter> {
        fun setProgressIndicator(active: Boolean)

        fun showPhotos(photos: List<Photo>)

        fun showError(error: String?)

        fun updateAutoComplete(suggests: Set<String>)

        fun shopPreviewDetail(photo: Photo)
    }

    interface Presenter : BasePresenter {
        //fun loadPhotosForLocation(location: Location)

        fun loadPhotos(search: String)

        fun openPhoto(photo: Photo)
    }

    interface Interactor : BaseInteractor {
        fun loadPhotos(search: String, onPhotosLoader: OnPhotosLoader)

        fun saveSuggest(word: String): Set<String>

        fun getSuggests(): Set<String>

        fun splitStringToWords(string: String): MutableSet<String>
    }
}