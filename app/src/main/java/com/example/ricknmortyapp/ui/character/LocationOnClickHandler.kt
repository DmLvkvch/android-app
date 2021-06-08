package com.example.ricknmortyapp.ui.character

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ricknmortyapp.R
import com.example.ricknmortyapp.model.entity.character.CharacterLocation
import com.example.ricknmortyapp.ui.location.LocationFragment


class LocationOnClickHandler {
    fun onClick(view: View, location: CharacterLocation) {
        val activity = view.context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction().apply {
            location.url?.split("/")?.last()?.let { LocationFragment(it.toInt()) }?.let {
                replace(
                    R.id.nav_host_fragment,
                    it
                )
            }
            addToBackStack(CharacterFragment::class.java.canonicalName)
            commit()
        }
    }
}