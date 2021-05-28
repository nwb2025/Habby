package com.example.hubby.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.hubby.R


class SettingsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view:View){
        val imgView = view.findViewById<ImageView>(R.id.person_img)
        Glide.with(view.context)
            .load("https://cdn.pixabay.com/photo/2018/08/28/12/41/avatar-3637425_960_720.png")
            .centerCrop()
            .error(R.drawable.ic_avatar)
            .into(imgView)
    }
}