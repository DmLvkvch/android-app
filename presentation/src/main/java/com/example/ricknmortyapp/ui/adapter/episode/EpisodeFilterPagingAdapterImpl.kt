package com.example.ricknmortyapp.ui.adapter.episode

import com.example.domain.entities.episode.EpisodeList
import com.example.domain.interactors.IEpisodeInteractor
import com.example.domain.repository.Info

class EpisodeFilterPagingAdapterImpl constructor(
    private val interactor: IEpisodeInteractor,
    private val name: String?, private val episode: String?
) :
    EpisodePagingAdapter() {

    var info: Info = Info()

    var page = 1

    override suspend fun getNextPagingData(): EpisodeList {
        val items =
            interactor.getEpisodesByFilter(page, name, episode)
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
        return info.next?.split("?page=")?.get(1)?.split("&")?.get(0)?.toInt() ?: -1
    }

    override fun reset() {
        page = 1
    }
}