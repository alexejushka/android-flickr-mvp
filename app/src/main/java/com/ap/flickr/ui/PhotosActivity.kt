package com.ap.flickr.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar



import com.ap.flickr.R
import com.ap.flickr.pojo.Photo
import kotlinx.android.synthetic.main.activity_main.*


class PhotosActivity : AppCompatActivity(), OnShowProgress {

    override fun openPreview(photo: Photo) {
        addFragmentInActivityAndAddToStack(PreviewFragment.newInstance(photo), R.id.frame_layout)
    }

    override fun onShowProgress(show: Boolean) {
        if (show) {
            toolbar_progress_bar.visibility = View.VISIBLE
        } else {
            toolbar_progress_bar.visibility = View.GONE
        }
    }

    fun AppCompatActivity.addFragmentInActivityAndAddToStack(fragment: Fragment, @IdRes frameId: Int) {
        supportFragmentManager.transact {
            setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_anim, R.anim.exit_anim)
            add(frameId, fragment)
            addToBackStack(null)
        }
    }


    fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
        supportFragmentManager.transact {
            replace(frameId, fragment)
        }
    }

    private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
        beginTransaction().apply {
            action()
        }.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.findFragmentById(R.id.frame_layout)
                    as PhotosFragment? ?: PhotosFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.frame_layout)
            }
        }
    }
}
