package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.location.LocationList
import com.example.domain.interactors.ILocationInteractor
import com.example.domain.repository.Info

class LocationFilterPagingAdapterImpl constructor(
    private val interactor: ILocationInteractor,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) :
    LocationPagingAdapter() {

    var info: Info = Info()
    var page = 1

    override suspend fun getNextPagingData(): LocationList {
        val charactersByFilter =
            interactor.getLocationsByFilter(page, name, type, dimension)
        info = charactersByFilter.info
        page = getNextPage()
        return charactersByFilter
    }

    override fun isNext(): Boolean {
        return info.next != null
    }

    override fun isLast(): Boolean {
        return info.next == null
    }

    override fun getNextPage(): Int {
        return info.next?.split("?page=")?.get(1)?.split("&")?.get(0)?.toInt() ?: -1
    }

    override fun reset() {
        page = 1
    }
}