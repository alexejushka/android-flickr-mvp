package com.ap.flickr.utils

import android.content.Context

class SharedPrefsHelper(private val context: Context) : OnSaveInterface {

    companion object {
        private const val SHARED_PREFS_NAME = "prefs"
        private const val SHARED_PREFS_KEY_SEARCH_SUGGEST = "search_suggests"
    }

    override fun getPreviousSearchSuggests(): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, 0)
        return sharedPreferences.getString(SHARED_PREFS_KEY_SEARCH_SUGGEST, "")
    }

    override fun saveSearchSuggest(search: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(SHARED_PREFS_KEY_SEARCH_SUGGEST, search)
            apply()
        }
    }

}
