package com.ap.flickr.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ap.flickr.utils.startGridActivity
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware

class SplashActivity : AppCompatActivity(), KodeinAware {

    override lateinit var kodein: Kodein

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startGridActivity()
        finish()
    }
}