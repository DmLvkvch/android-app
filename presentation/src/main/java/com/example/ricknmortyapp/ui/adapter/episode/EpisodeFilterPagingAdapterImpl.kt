package com.example.ricknmortyapp.ui.adapter.episode

import com.example.domain.entities.character.CharacterList
import com.example.domain.entities.episode.EpisodeList
import com.example.domain.interactors.IEpisodeInteractor
import com.example.domain.repository.Info

class EpisodeFilterPagingAdapterImpl constructor(
    private val interactor: IEpisodeInteractor,
    private val name: String, private val episode: String
) :
    EpisodePagingAdapter() {

    lateinit var info: Info

    override suspend fun getNextPagingData(): EpisodeList {
        val items =
            interactor.getEpisodesByFilter(name, episode)
        info = items.info
        return items
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