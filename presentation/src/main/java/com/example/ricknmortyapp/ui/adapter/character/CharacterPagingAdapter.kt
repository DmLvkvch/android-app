package com.example.ricknmortyapp.ui.adapter.character

import com.example.domain.entities.character.CharacterList

abstract class CharacterPagingAdapter {

    abstract suspend fun getNextPagingData(): CharacterList

    abstract fun isNext(): Boolean

    abstract fun isLast(): Boolean

    abstract fun getNextPage(): Int

    abstract fun reset()
}