package com.example.ricknmortyapp.ui.adapter.episode

import com.example.domain.entities.episode.EpisodeList
import com.example.domain.interactors.IEpisodeInteractor
import com.example.domain.repository.Info

class EpisodeIdsPagingAdapterImpl constructor(
    private val interactor: IEpisodeInteractor,
    private val ids: String
) :
    EpisodePagingAdapter() {

    override suspend fun getNextPagingData(): EpisodeList {
        val items = interactor.getEpisodesByIds(ids)
        return EpisodeList(Info(items.size, 1), items)
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

    override fun reset() {

    }
}