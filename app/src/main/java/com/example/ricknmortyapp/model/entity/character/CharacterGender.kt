package com.example.ricknmortyapp.model.entity.character

import com.google.gson.annotations.SerializedName

enum class CharacterGender(val value: String) {
    @SerializedName("Female")
    FEMALE ("Female"),
    @SerializedName("Male")
    MALE ("Male"),
    @SerializedName("Genderless")
    GENDERLESS("Genderless"),
    @SerializedName("unknown")
    UNKNOWN("unknown")
}