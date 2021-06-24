package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.character.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCharacters(persons: MutableList<Character>)

    @Query("SELECT * FROM character WHERE id=:id")
    suspend fun findCharacterById(id: Int): Character?

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): MutableList<Character>?

    @Query("SELECT * FROM character WHERE id in (:ids)")
    suspend fun getCharactersByIds(ids: MutableList<Int>): MutableList<Character>?

    @Query("SELECT * FROM character LIMIT (:from), (:to)")
    suspend fun getCharactersByPage(from: Int, to: Int): MutableList<Character>?

    @Query("SELECT * FROM character WHERE name=:name AND status=:status AND species=:species AND type=:type AND gender=:gender")
    suspend fun getCharactersByFilterParams(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): MutableList<Character>?

}