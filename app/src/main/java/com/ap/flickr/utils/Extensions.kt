package com.ap.flickr.utils

import android.content.Context
import android.content.Intent
import android.databinding.BindingAdapter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.ap.flickr.ui.PhotosActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.startGridActivity() {
    val intent = Intent(this, PhotosActivity::class.java)
    startActivity(intent)
}

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(url:String) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}