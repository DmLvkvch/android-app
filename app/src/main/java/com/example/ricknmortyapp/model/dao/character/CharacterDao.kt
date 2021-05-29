package com.example.ricknmortyapp.model.dao.character

import androidx.room.Dao
import androidx.room.Query
import com.example.ricknmortyapp.model.entity.character.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Character>
}