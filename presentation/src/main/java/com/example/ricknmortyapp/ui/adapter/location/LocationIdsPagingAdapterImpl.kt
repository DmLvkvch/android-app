package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.location.LocationList
import com.example.domain.interactors.ILocationInteractor
import com.example.domain.repository.Info

class LocationIdsPagingAdapterImpl constructor(
    private val interactor: ILocationInteractor,
    private val ids: String
) :
    LocationPagingAdapter() {

    override suspend fun getNextPagingData(): LocationList {
        val items = interactor.getLocationsByIds(ids)
        return LocationList(Info(items.size, 1), items)
    }

    override fun isNext(): Boolean {
        return false
    }

    override fun isLast(): Boolean {
        return true
    }

    override fun getNextPage(): Int {
        return -1
    }
}