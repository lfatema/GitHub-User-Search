package com.example.secondexercise.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.secondexercise.R
import com.squareup.picasso.Picasso

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val displayName = intent.getStringExtra("login")
        val displayImg = intent.getStringExtra("avatarURL")

        val name = findViewById<TextView>(R.id.TextView_username)
        name.text = displayName

        val avatar = findViewById<ImageView>(R.id.ImageView_avatar)
        Picasso.get().load(displayImg).into(avatar)

        }
}
