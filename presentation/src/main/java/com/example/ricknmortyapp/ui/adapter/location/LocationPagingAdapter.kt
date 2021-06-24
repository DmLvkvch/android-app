package com.example.ricknmortyapp.ui.adapter.location

import com.example.domain.entities.character.CharacterList
import com.example.domain.entities.location.LocationList
import com.example.domain.repository.Info

abstract class LocationPagingAdapter {

    abstract suspend fun getNextPagingData(): LocationList

    abstract fun isNext(): Boolean

    abstract fun isLast(): Boolean

    abstract fun getNextPage(): Int
}