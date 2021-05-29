package com.example.ricknmortyapp.model.entity.character

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey val uid: Int,
    val id: Int = -1,
    val name: String? = null,
    val status: CharacterStatus = CharacterStatus.UNKNOWN,
    val species: String? = null,
    val type: String? = null,
    val gender: CharacterGender = CharacterGender.UNKNOWN,
    val origin: CharacterOrigin? = null,
    val image: String? = null,
    val episode: ArrayList<String>? = null,
    val url: String? = null,
    val created: String? = null
)
