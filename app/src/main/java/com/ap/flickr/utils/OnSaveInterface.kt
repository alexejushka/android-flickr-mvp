package com.ap.flickr.utils

interface OnSaveInterface {
    fun getPreviousSearchSuggests(): String
    fun saveSearchSuggest(search: String)
}