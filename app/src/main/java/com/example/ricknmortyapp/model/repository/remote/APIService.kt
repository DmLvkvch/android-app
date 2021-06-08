package com.example.ricknmortyapp.model.repository.remote

import com.example.ricknmortyapp.model.entity.character.Character
import com.example.ricknmortyapp.model.entity.character.CharacterList
import com.example.ricknmortyapp.model.entity.location.Location
import com.example.ricknmortyapp.model.entity.location.LocationList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Character>

    @GET("location/{id}")
    suspend fun getLocationDetails(@Path("id") id: Int): Response<Location>

    @GET("location")
    suspend fun getLocationsByPage(@Query("page") page: Int): Response<LocationList>

}