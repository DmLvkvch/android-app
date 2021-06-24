package com.example.ricknmortyapp.ui.adapter.character

import com.example.domain.entities.character.CharacterList
import com.example.domain.interactors.ICharacterInteractor
import com.example.domain.repository.Info

class CharacterFilterPagingAdapterImpl constructor(
    private val interactor: ICharacterInteractor,
    private val name: String,
    private val status: String,
    private val species: String,
    private val type: String,
    private val gender: String
) :
    CharacterPagingAdapter() {

    lateinit var info: Info

    override suspend fun getNextPagingData(): CharacterList {
        val charactersByFilter =
            interactor.getCharactersByFilter(name, status, species, type, gender)
        info = charactersByFilter.info
        return charactersByFilter
    }

    override fun isNext(): Boolean {
        return info.next!=null
    }

    override fun isLast(): Boolean {
        return info.next == null
    }

    override fun getNextPage(): Int {
        return 1
    }
}