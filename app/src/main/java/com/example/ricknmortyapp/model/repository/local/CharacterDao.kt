package com.example.ricknmortyapp.model.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ricknmortyapp.model.entity.character.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    suspend fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:userIds)")
    suspend fun getAllByIds(userIds: IntArray): List<Character>

    @Insert
    suspend fun insertAll(vararg characters: Character)

    @Query("DELETE FROM character")
    suspend fun clearAllCharacters()
}