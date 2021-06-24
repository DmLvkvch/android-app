package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.episode.Episode
import com.example.domain.entities.location.Location

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllLocations(value: MutableList<Location>)

    @Query("SELECT * FROM location WHERE id=:id")
    suspend fun findLocationById(id: Int): Location?

    @Query("SELECT * FROM location")
    suspend fun getAllLocations(): MutableList<Location>?

    @Query("SELECT * FROM location WHERE id in (:ids)")
    suspend fun getLocationsByIds(ids: MutableList<Int>): MutableList<Location>?

    @Query("SELECT * FROM location LIMIT (:from), (:to)")
    suspend fun getLocationsByPage(from: Int, to: Int): MutableList<Location>?

    @Query("SELECT * FROM location WHERE name=:name AND type=:type AND dimension=:dimension")
    suspend fun getEpisodesByFilterParams(
        name: String,
        type: String,
        dimension: String
    ): MutableList<Episode>?
}