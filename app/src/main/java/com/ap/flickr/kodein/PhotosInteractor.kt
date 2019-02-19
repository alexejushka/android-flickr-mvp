package com.ap.flickr.kodein

import com.ap.flickr.PhotosContract
import com.ap.flickr.network.FlickrPhotoLoader
import com.ap.flickr.network.OnPhotosLoader
import com.ap.flickr.utils.OnSaveInterface

class PhotosInteractor(val networkInterface: NetworkInterface, val onSaveInterface: OnSaveInterface) :
    PhotosContract.Interactor {

    override fun loadPhotos(search: String, onPhotosLoader: OnPhotosLoader) {
        FlickrPhotoLoader(networkInterface.getApi(), onPhotosLoader).rxAction(search)
    }

    override fun saveSuggest(word: String): Set<String> {
        val set: MutableSet<String> = splitStringToWords(onSaveInterface.getPreviousSearchSuggests())
        set.add(word)
        onSaveInterface.saveSearchSuggest(set.joinToString())
        return set
    }

    override fun getSuggests(): Set<String> {
        return splitStringToWords(onSaveInterface.getPreviousSearchSuggests())
    }

    override fun splitStringToWords(string: String) = string.split(",").map { it.trim() }.toMutableSet()

}