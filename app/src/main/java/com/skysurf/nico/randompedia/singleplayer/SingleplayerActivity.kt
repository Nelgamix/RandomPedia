package com.skysurf.nico.randompedia.singleplayer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.skysurf.nico.randompedia.R

class SingleplayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleplayer)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SingleplayerActivity::class.java)
        }
    }
}
