package com.example.ricknmortyapp.ui.adapter.character

import com.example.domain.entities.character.CharacterList
import com.example.domain.interactors.ICharacterInteractor
import com.example.domain.repository.Info

class CharacterIdsPagingAdapterImpl constructor(
    private val interactor: ICharacterInteractor,
    private val ids: String
) :
    CharacterPagingAdapter() {

    override suspend fun getNextPagingData(): CharacterList {
        val items = interactor.getCharactersByIds(ids)
        return CharacterList(Info(items.size, 1), items)
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
}