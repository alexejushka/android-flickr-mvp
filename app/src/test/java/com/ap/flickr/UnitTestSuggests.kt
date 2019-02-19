package com.ap.flickr

import android.content.Context
import com.ap.flickr.kodein.NetworkInterface
import com.ap.flickr.kodein.PhotosInteractor
import com.ap.flickr.network.RestClient
import com.ap.flickr.utils.OnSaveInterface
import com.ap.flickr.utils.SharedPrefsHelper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnitTestSuggests {

    @Mock
    lateinit var mMockContext: Context

    lateinit var interactor: PhotosContract.Interactor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val networkInterface: NetworkInterface = RestClient()
        val onSaveInterface: OnSaveInterface = SharedPrefsHelper(mMockContext)
        interactor = PhotosInteractor(networkInterface, onSaveInterface)
    }

    @Test
    fun testStringSplitting() {
        var set: MutableSet<String> = interactor.splitStringToWords("searchstring")
        //interactor.saveSuggest("test")
        //var set: Set<String> = interactor.getSuggests()
        assertEquals(set.contains("searchstring"), true)
    }
}