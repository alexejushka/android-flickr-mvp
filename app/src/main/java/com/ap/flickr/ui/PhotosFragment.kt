package com.ap.flickr.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import com.ap.flickr.FlickrApp
import com.ap.flickr.PhotosContract
import com.ap.flickr.R
import com.ap.flickr.databinding.ContentMainBinding
import com.ap.flickr.pojo.Photo
import com.ap.flickr.utils.hideKeyboard
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton


class PhotosFragment : Fragment(), PhotosContract.View, KodeinAware {

    override lateinit var kodein: Kodein
    override lateinit var presenter: PhotosContract.Presenter
    lateinit var adapter: ArrayAdapter<String>
    lateinit var photoAdapter: PhotosRecyclerViewAdapter
    lateinit var binding: ContentMainBinding
    lateinit var listener: OnShowProgress

    companion object {
        fun newInstance() = PhotosFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodein = (activity!!.application as FlickrApp).appModule.kodein
        presenter = PhotosPresenter(kodein, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.content_main, container, false)
        val view = binding.root

        photoAdapter = PhotosRecyclerViewAdapter(mutableListOf(), presenter)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = photoAdapter

        adapter = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, mutableListOf())
        binding.searchTextView.setAdapter<ArrayAdapter<String>>(adapter)
        binding.searchTextView.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                textView.hideKeyboard()
                presenter.loadPhotos(textView.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnShowProgress) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnShowProgress")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()

        binding.searchTextView.setText("saint petersburg")
        presenter.loadPhotos(binding.searchTextView.text.toString())
    }

    override fun shopPreviewDetail(photo: Photo) {
        binding.searchTextView.hideKeyboard()
        listener.openPreview(photo)
    }

    override fun updateAutoComplete(suggests: Set<String>) {
        adapter.apply {
            clear()
            addAll(suggests)
        }
    }

    override fun showPhotos(photos: List<Photo>) {
        binding.photos = photos
        photoAdapter.setPhotos(photos)
    }

    override fun showError(error: String?) {
        alert("$error") {
            yesButton {}
        }.show()
    }

    override fun setProgressIndicator(active: Boolean) {
        listener.onShowProgress(active)
    }

}