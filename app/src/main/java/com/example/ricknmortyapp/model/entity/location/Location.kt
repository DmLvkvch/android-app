package com.example.ricknmortyapp.model.entity.location

data class Location(
    val id: Int = -1,
    val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
    val residents: ArrayList<String>? = null,
    val url: String? = null,
    val created: String? = null,
)
