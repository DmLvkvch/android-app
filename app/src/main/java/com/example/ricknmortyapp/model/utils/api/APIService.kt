package com.example.ricknmortyapp.model.utils.api

import com.example.ricknmortyapp.model.entity.character.Character
import com.example.ricknmortyapp.model.entity.character.CharacterList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {

    @GET("character")
    fun getAllUnits(): Call<CharacterList>

    @GET("character")
    fun getUnitsByPage(@Query("page") page: Int): Call<CharacterList>

    @GET("/character/{id}")
    fun getSingleUnit(@Path("id") id: Int): Call<Character>

}