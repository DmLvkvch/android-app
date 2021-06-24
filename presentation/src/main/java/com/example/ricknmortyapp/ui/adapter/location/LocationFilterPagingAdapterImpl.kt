package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.location.LocationList
import com.example.domain.interactors.ILocationInteractor
import com.example.domain.repository.Info

class LocationFilterPagingAdapterImpl constructor(
    private val interactor: ILocationInteractor,
    private val name: String,
    private val status: String,
    private val species: String
) :
    LocationPagingAdapter() {

    private lateinit var info: Info

    override suspend fun getNextPagingData(): LocationList {
        val charactersByFilter =
            interactor.getLocationsByFilter(name, status, species)
        info = charactersByFilter.info
        return charactersByFilter
    }

    override fun isNext(): Boolean {
        return info.next != null
    }

    override fun isLast(): Boolean {
        return info.next == null
    }

    override fun getNextPage(): Int {
        return 1
    }
}