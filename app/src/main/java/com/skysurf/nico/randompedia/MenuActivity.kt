package com.skysurf.nico.randompedia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.skysurf.nico.randompedia.singleplayer.SingleplayerActivity

class MenuActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun onStart() {
        super.onStart()

        val user = mAuth.currentUser
    }

    fun singleplayer(view: View) {
        startActivity(SingleplayerActivity.newIntent(this))
    }
}
