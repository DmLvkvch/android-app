package com.example.ricknmortyapp.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ricknmortyapp.model.entity.character.CharacterGender
import com.example.ricknmortyapp.model.entity.character.CharacterStatus


@BindingAdapter(value = ["app:url"])
fun urlToImageView(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter(value = ["android:text"])
fun genderToTextView(view: TextView, gender: CharacterGender?) {
    view.text = gender?.name ?: CharacterGender.UNKNOWN.name
}

@BindingAdapter(value = ["android:text"])
fun statusToTextView(view: TextView, gender: CharacterStatus?) {
    view.text = gender?.name ?: CharacterStatus.UNKNOWN.name
}
