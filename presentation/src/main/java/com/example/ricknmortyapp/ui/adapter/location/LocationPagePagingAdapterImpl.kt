package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.location.LocationList
import com.example.domain.interactors.ILocationInteractor
import com.example.domain.repository.Info

class LocationPagePagingAdapterImpl constructor(private val interactor: ILocationInteractor) :
    LocationPagingAdapter() {

    lateinit var info: Info

    var page: Int = 1

    override suspend fun getNextPagingData(): LocationList {
        val items = interactor.getAllLocationsByPage(page)
        info = items.info
        page = getNextPage()
        return items
    }

    override fun isNext(): Boolean {
        return info.next != null
    }

    override fun isLast(): Boolean {
        return info.next == null
    }

    override fun getNextPage(): Int {
        return this.info.next?.split("=")?.last()?.toInt() ?: -1
    }

    override fun reset() {
        page = 1
    }
}