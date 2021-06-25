package com.example.ricknmortyapp.ui.adapter.episode

import com.example.domain.entities.episode.EpisodeList
import com.example.domain.interactors.IEpisodeInteractor
import com.example.domain.repository.Info

class EpisodePagePagingAdapterImpl constructor(private val interactor: IEpisodeInteractor) :
    EpisodePagingAdapter() {

    lateinit var info: Info

    var page: Int = 1

    override suspend fun getNextPagingData(): EpisodeList {
        val allCharactersByPage = interactor.getAllEpisodesByPage(page)
        info = allCharactersByPage.info
        page = getNextPage()
        return allCharactersByPage
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