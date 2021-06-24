package com.example.data.network

import com.example.domain.entities.character.Character
import com.example.domain.entities.character.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Character>

    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): Response<MutableList<Character>>

    @GET("character")
    suspend fun getCharactersByFilterParams(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("gender") gender: String
    ): Response<CharacterList>

}