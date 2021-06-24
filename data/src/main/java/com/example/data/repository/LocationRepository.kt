package com.example.data.repository

import com.example.data.database.DatabaseStorage
import com.example.data.network.LocationApiService
import com.example.domain.entities.location.Location
import com.example.domain.entities.location.LocationList
import com.example.domain.repository.ILocationRepository
import com.example.domain.repository.Info
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val databaseStorage: DatabaseStorage,
    private val api: LocationApiService
) : ILocationRepository {

    override suspend fun getAllLocationsByPage(page: Int): LocationList = try {
        val results = api.getLocationsByPage(page).body()
        results?.let { databaseStorage.locationDao.saveAllLocations(it.results) }
        results ?: throw IllegalStateException()
    } catch (e: Exception) {
        val results =
            (databaseStorage.locationDao.getLocationsByPage((page - 1) * 20, page * 20)
                ?: throw IllegalStateException())
        LocationList(Info(count = results.size, pages = 1), results)

    }

    override suspend fun getAllLocations(): MutableList<Location> = try {
        api.getLocationsByPage(1).body()?.results ?: throw IllegalStateException()
    } catch (e: Exception) {
        databaseStorage.locationDao.getAllLocations() ?: throw IllegalStateException()
    }

    override suspend fun getLocationById(id: Int): Location = try {
        api.getLocationDetails(id).body() ?: throw IllegalStateException()
    } catch (e: Exception) {
        databaseStorage.locationDao.findLocationById(id) ?: throw IllegalStateException()
    }

    override suspend fun getLocationsByIds(ids: String): MutableList<Location> = try {
        throw IllegalStateException()
    } catch (e: Exception) {
        throw IllegalStateException()
    }

    override suspend fun getLocationsByFilterParams(
        name: String,
        status: String,
        species: String
    ): LocationList = try {
        api.getLocationsByFilterParams(name, status, species).body()
            ?: throw IllegalStateException()
    } catch (e: Exception) {
        throw IllegalStateException()
    }

}