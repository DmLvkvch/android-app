package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.location.LocationList

abstract class LocationPagingAdapter {

    abstract suspend fun getNextPagingData(): LocationList

    abstract fun isNext(): Boolean

    abstract fun isLast(): Boolean

    abstract fun getNextPage(): Int

    abstract fun reset()
}