package com.example.domain.repository

import com.example.domain.entities.character.Character
import com.example.domain.entities.episode.Episode
import com.example.domain.entities.location.Location

interface IRepository {
    suspend fun getAllCharactersByPage(page: Int = -1): MutableList<Character>?

    suspend fun getAllCharacters(): MutableList<Character>?

    suspend fun getCharacterById(id: Int = 1): Character?

    suspend fun getCharactersByIds(ids: String): MutableList<Character>?

    suspend fun getAllLocationsByPage(page: Int = -1): MutableList<Location>?

    suspend fun getAllLocations(): MutableList<Location>?

    suspend fun getLocationById(id: Int = 1): Location?

    suspend fun getLocationsByIds(ids: String): MutableList<Location>?

    suspend fun getCharactersByFilterParams(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): MutableList<Character>?

    suspend fun getAllEpisodesByPage(page: Int = -1): MutableList<Episode>?

    suspend fun getAllEpisodes(): MutableList<Episode>?

    suspend fun getEpisodeById(id: Int = 1): Episode?

    suspend fun getEpisodesByIds(ids: String): MutableList<Episode>?
}