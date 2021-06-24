package com.example.ricknmortyapp.ui.adapter.character

import com.example.domain.entities.character.CharacterList
import com.example.domain.interactors.ICharacterInteractor
import com.example.domain.repository.Info
import javax.inject.Inject

class CharacterPagePagingAdapterImpl constructor(private val interactor: ICharacterInteractor) :
    CharacterPagingAdapter() {

    lateinit var info: Info

    var page: Int = 1

    override suspend fun getNextPagingData(): CharacterList {
        val allCharactersByPage = interactor.getAllCharactersByPage(page)
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
}