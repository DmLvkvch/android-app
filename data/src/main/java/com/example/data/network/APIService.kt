package com.example.data.network

import com.example.domain.entities.character.Character
import com.example.domain.entities.character.CharacterList
import com.example.domain.entities.episode.Episode
import com.example.domain.entities.episode.EpisodeList
import com.example.domain.entities.location.Location
import com.example.domain.entities.location.LocationList
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

    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): Response<MutableList<Character>>

    @GET("character")
    suspend fun getCharactersByFilterParams(@Query("name") name: String,
                                            @Query("status") status: String,
                                            @Query("species") species: String,
                                            @Query("type") type: String,
                                            @Query("gender") gender: String,
                                            ): Response<MutableList<Character>>

    @GET("episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") id: Int): Response<Episode>

    @GET("episode")
    suspend fun getEpisodesByPage(@Query("page") page: Int): Response<EpisodeList>

    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") ids: String): Response<MutableList<Episode>>
}