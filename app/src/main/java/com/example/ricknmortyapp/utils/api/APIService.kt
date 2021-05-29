package com.example.ricknmortyapp.utils.api

import com.example.ricknmortyapp.model.entity.character.Character
import com.example.ricknmortyapp.model.entity.character.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {

    @GET("/character")
    fun getAllUnits(): Call<CharacterList>

    @GET("/character/{id}")
    fun getSingleUnit(@Path("id") id: Int): Call<Character>

}