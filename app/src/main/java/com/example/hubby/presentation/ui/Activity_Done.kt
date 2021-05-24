package com.example.hubby.presentation.ui

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.hubby.R

class Activity_Done : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__done)
        val img_done = findViewById<ImageView>(R.id.img_done)
        val drawable: Drawable = img_done.drawable

        if ( drawable is AnimatedVectorDrawableCompat){
            var avd = drawable as AnimatedVectorDrawableCompat
            avd.start()
        }else if ( drawable is AnimatedVectorDrawable){
            var avd = drawable as AnimatedVectorDrawable
            avd.start()
        }
    }
}