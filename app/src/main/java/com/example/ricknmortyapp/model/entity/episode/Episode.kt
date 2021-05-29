package com.example.ricknmortyapp.model.entity.episode

data class Episode(
    val id: Int = -1,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null,
    val characters: ArrayList<String>? = null,
    val url: String? = null,
    val created: String? = null,
)
