package com.example.ricknmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.character.Character
import com.example.domain.interactors.ICharacterInteractor
import com.example.domain.repository.Resource
import com.example.ricknmortyapp.ui.BaseViewModel
import com.example.ricknmortyapp.ui.adapter.character.CharacterFilterPagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.character.CharacterIdsPagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.character.CharacterPagePagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.character.CharacterPagingAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterListViewModel @Inject constructor(private val interactor: ICharacterInteractor) :
    BaseViewModel<MutableList<Character>>() {

    var items: MutableLiveData<Resource<MutableList<Character>>> = MutableLiveData()

    var characters: MutableList<Character> = mutableListOf()

    var isLoading = false

    var adapter: CharacterPagingAdapter = CharacterPagePagingAdapterImpl(interactor)

    fun fetch() {
        adapter.reset()
        reset()
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        isLoading = true
        fetchCharacters()
        isLoading = false
    }

    fun getCharacters(ids: String) = viewModelScope.launch {
        adapter = CharacterIdsPagingAdapterImpl(interactor, ids)
        fetchCharacters(ids)
    }

    private suspend fun fetchCharacters() {
        items.postValue(Resource.Loading())
        try {
            val response = adapter.getNextPagingData()
            response.let { items.value?.data?.addAll(it.results) }
            characters.addAll(response.results)
            items.postValue(Resource.Success(characters))
        } catch (t: Throwable) {
        }
    }

    private suspend fun fetchCharacters(ids: String) {
        items.postValue(Resource.Loading())
        try {
            val response = interactor.getCharactersByIds(ids)
            items.postValue(handleResponse(response))
        } catch (t: Throwable) {
            items.postValue(t.message?.let { Resource.Error(it) })
        }
    }

    fun isLastPage(): Boolean {
        return adapter.isLast()
    }

    fun reset() {
        items.postValue(Resource.Loading())
        characters.clear()
    }

    fun filter(filter: CharacterFilter) {
        adapter = CharacterFilterPagingAdapterImpl(
            interactor, filter.name, filter.status,
            filter.species, filter.type, filter.gender
        )
        reset()
        getCharacters()
    }
}