package com.example.ricknmortyapp.ui.adapter.episode

import com.example.domain.entities.episode.EpisodeList

abstract class EpisodePagingAdapter {

    abstract suspend fun getNextPagingData(): EpisodeList

    abstract fun isNext(): Boolean

    abstract fun isLast(): Boolean

    abstract fun getNextPage(): Int
}