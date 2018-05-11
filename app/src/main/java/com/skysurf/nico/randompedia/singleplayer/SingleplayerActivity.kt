package com.skysurf.nico.randompedia.singleplayer

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.skysurf.nico.randompedia.R
import com.skysurf.nico.randompedia.model.RandomArticle

class SingleplayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleplayer)

        NetworkRA().execute()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SingleplayerActivity::class.java)
        }
    }

    class NetworkRA : AsyncTask<Void, Void, Void>() {
        private val ra = RandomArticle()

        override fun doInBackground(vararg params: Void?): Void? {
            ra.launch()
            return null
        }
    }
}
